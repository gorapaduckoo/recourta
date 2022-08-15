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
import com.ssafy.recourta.global.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


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

    @Autowired
    private ImgUtil imgUtil;

    @Override
    public LectureResponse.LectureId createLecture(LectureRequest.LectureCreateForm input, MultipartFile lectureImg) throws Exception {
        // RequestDto에 담긴 userId로 user 객체 조회
        User user = userRepository.findById(input.getUserId()).orElseThrow(UserNotFoundException::new);

        // 조회한 user 객체는 저장할 lecture 객체에 담아줌
        Lecture lecture = input.toEntity(user);
        imgUtil.uploadImage(lecture, lectureImg);

        // DB 저장 후 저장한 lecture의 lectureId를 결과값으로 반환
        System.out.println(lecture.getLectureTime());
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
            return result.toLectureDetail();
        }
        else {
            throw new LectureException.NullLecture();
        }
    }


    @Override
    public LectureResponse.LectureId updateLecture(Integer lectureId, LectureRequest.LectureUpdateForm lecture, MultipartFile lectureImg) throws Exception {
        Lecture updatedLecture = lectureRepository.findById(lectureId).orElseThrow(
                ()-> new LectureException.UnvalidLectureId(lectureId));
        updatedLecture.update(lecture.getContent(), lecture.getStartDate(), lecture.getEndDate(), lecture.getLectureTime().toString());
        if(!lectureImg.isEmpty()) {
            imgUtil.uploadImage(updatedLecture, lectureImg);
        }
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

            Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                    ()-> new LectureException.UnvalidLectureId(lectureId));
            imgUtil.deleteImage(lecture.getLectureImg(), "lecture");
            lectureRepository.deleteById(lectureId);

            return LectureResponse.LectureId.builder().lectureId(lectureId).build();
        }
        else {
            throw new LectureException.UnvalidLectureId(lectureId);
        }
    }

    @Override
    public List<LectureResponse.LectureDetail> searchMyCurrentTeachingLecture(Integer userId) {
        // 존재하는 회원인 경우
        if(userRepository.existsById(userId)) {
            List<Lecture> searchResult = lectureRepository.findAllByUser_UserIdAndEndDateAfter(userId, LocalDate.now().minusDays(1));
//            List<Lecture> searchResult = lectureRepository.findAllByUser_UserIdAndStartDateBeforeAndEndDateAfter(userId, LocalDate.now().plusDays(1), LocalDate.now().minusDays(1));

            // 개설중인 강의가 없는 경우, 빈 리스트 반환
            List<LectureResponse.LectureDetail> result = new ArrayList<>();
            for (Lecture l: searchResult){
                result.add(l.toLecturePreview());
            }
            return result;

        // 존재하지 않는 회원인 경우
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<LectureResponse.LectureDetail> searchMyLecture(Integer userId) throws ParseException {
        List<LectureResponse.LectureDetail> result  = new ArrayList<>();
        if(userRepository.existsById(userId)) {
//            List<Lecture> lectures = registrationService.getCurrentLecturesOfUser(userId).getLectureList();
//            for (Lecture l : lectures) {
//                result.add(l.toLecturePreview());
//            }
            result = registrationService.getCurrentLecturesOfUser(userId).getLectureList();
        } else {
            throw new UserNotFoundException();
        }
        return result;
    }

    @Override
    public List<LectureResponse.LectureDetail> searchAvailableLecture() throws Exception {

        List<LectureResponse.LectureDetail> result = new ArrayList<>();
        List<Lecture> lectures = lectureRepository.findAllByStartDateAfter(LocalDate.now());

        // 수강 신청 가능한 강의가 없을 경우 빈 리스트 return
        for (Lecture l : lectures) {
            result.add(l.toLectureDetail());
        }
        return result;
    }


}