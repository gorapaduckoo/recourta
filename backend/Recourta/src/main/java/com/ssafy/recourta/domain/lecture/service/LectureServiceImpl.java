package com.ssafy.recourta.domain.lecture.service;

import com.ssafy.recourta.domain.lecture.dto.request.LectureRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;
import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import com.ssafy.recourta.domain.registration.repository.RegistrationRepository;
import com.ssafy.recourta.domain.registration.service.RegistrationService;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
import com.ssafy.recourta.global.exception.LectureException;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.recourta.global.util.LectureUtil.stringToJsonArray;

@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationService registrationService;

    @Override
    public LectureResponse.LectureId createLecture(LectureRequest.LectureCreateForm input) {
        // RequestDto에 담긴 userId로 user 객체 조회
        User user = userRepository.findById(input.getUserId()).orElseThrow(UserNotFoundException::new);

        // 조회한 user 객체는 저장할 lecture 객체에 담아줌
        Lecture lecture = Lecture.builder()
                .user(user)
                .title(input.getTitle())
                .content(input.getContent())
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .lectureImg(input.getLectureImg())
                .lectureTime(input.getLectureTime().toString())
                .build();

        // DB 저장 후 저장한 lecture의 lectureId를 결과값으로 반환
        Integer result = lectureRepository.save(lecture).getLectureId();
        if(result!=null){
            return LectureResponse.LectureId.builder().lectureId(result).build();
        } else {
            throw new LectureException.LectureSaveFail();
        }
    }



    @Override
    public LectureResponse.LectureDetail searchByLectureId(Integer lectureId) {
        Lecture result = lectureRepository.findById(lectureId).orElseThrow(
                ()-> new LectureException.UnvalidLectureId(lectureId));
        if (result != null) {

            return LectureResponse.LectureDetail.builder()
                    .lectureId(result.getLectureId())
                    .userId(result.getUser().getUserId())
                    .title(result.getTitle())
                    .content(result.getContent())
                    .startDate(result.getStartDate())
                    .endDate(result.getEndDate())
                    .lectureImg(result.getLectureImg())
                    .lectureTime(stringToJsonArray(result.getLectureTime()))
                    .build();

        }
        else {
            throw new LectureException.NullLecture();
        }
    }


    @Override
    public LectureResponse.LectureId updateLecture(Integer lectureId, LectureRequest.LectureUpdateForm lecture) throws Exception {
        Lecture updatedLecture = lectureRepository.findById(lectureId).orElseThrow(
                ()-> new LectureException.UnvalidLectureId(lectureId));
        updatedLecture.update(lecture.getContent(), lecture.getStartDate(), lecture.getEndDate(), lecture.getLectureImg(), lecture.getLectureTime().toString());
        Integer result = lectureRepository.save(updatedLecture).getLectureId();


        if (result != null) {
            return LectureResponse.LectureId.builder()
                    .lectureId(result)
                    .build();
        } else {
            throw new LectureException.LectureSaveFail();
        }
    }

    @Override
    public LectureResponse.LectureId deleteLecture(Integer lectureId) {
        if(lectureRepository.existsById(lectureId)) {
            lectureRepository.deleteById(lectureId);


            return LectureResponse.LectureId.builder().lectureId(lectureId).build();
        }
        else {
            throw new LectureException.UnvalidLectureId(lectureId);
        }
    }

    @Override
    public List<LectureResponse.LecturePreview> searchMyCurrentTeachingLecture(Integer userId) {
        // 존재하는 회원인 경우
        if(userRepository.existsById(userId)) {
            List<Lecture> searchResult = lectureRepository.findAllByUser_UserIdAndStartDateBeforeAndEndDateAfter(userId, LocalDate.now().plusDays(1), LocalDate.now().minusDays(1));
            if(searchResult.size() == 0) {
                throw new LectureException.NullLecture();
            } else {
                List<LectureResponse.LecturePreview> result = new ArrayList<>();
                for (Lecture l: searchResult){
                    result.add(LectureResponse.LecturePreview.builder()
                            .lectureId(l.getLectureId())
                            .title(l.getTitle())
                            .lectureTime(stringToJsonArray(l.getLectureTime()))
                            .build());
                }
                return result;
            }
        // 존재하지 않는 회원인 경우
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<LectureResponse.LecturePreview> searchMyLecture(Integer userId) throws ParseException {
        List<LectureResponse.LecturePreview> result  = new ArrayList<>();
        if(userRepository.existsById(userId)) {
            List<Lecture> lectures = registrationService.getCurrentLecturesOfUser(userId).getLectureList();
            for (Lecture l : lectures) {
                result.add(LectureResponse.LecturePreview.builder()
                        .title(l.getTitle())
                        .teacher(l.getUser().getName())
                        .lectureTime(stringToJsonArray(l.getLectureTime()))
                        .lectureId(l.getLectureId())
                        .build());
            }
        } else {
            throw new UserNotFoundException();
        }
        return result;
    }


}