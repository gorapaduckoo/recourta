package com.ssafy.recourta.domain.lecture.service;

import com.ssafy.recourta.domain.lecture.dto.request.LectureRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;
import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import com.ssafy.recourta.domain.registration.repository.RegistrationRepository;
import com.ssafy.recourta.domain.registration.service.RegistrationService;
import com.ssafy.recourta.domain.session.repository.SessionRepository;
import com.ssafy.recourta.domain.session.service.SessionService;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
import com.ssafy.recourta.global.exception.LectureException;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import com.ssafy.recourta.global.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {

    private static final Logger logger = LoggerFactory.getLogger(LectureServiceImpl.class);

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

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
        if(lectureImg != null) {
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

    @Transactional
    @Override
    public LectureResponse.LectureId deleteLecture(Integer lectureId) {

        logger.info("service in");
        if(lectureRepository.existsById(lectureId)) {

            logger.info("lectureid exists");

            Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                    ()-> new LectureException.UnvalidLectureId(lectureId));
            
            // 현재 시간 이후의 세션은 싹 다 삭제 처리

            // 개강일이 오늘 이후인 강의
            if(lecture.getStartDate().isAfter(LocalDate.now().minusDays(1))) {
                Integer cnt = sessionRepository.countByLecture_LectureIdAndStartTimeBefore(lecture.getLectureId(), LocalDateTime.now());
                if (cnt == 0) { // 만약 아직 시작되지 않은 강의라면 강의 삭제 처리
                    sessionRepository.deleteAllByLecture_LectureIdAndStartTimeAfter(lectureId, LocalDateTime.now());
                    imgUtil.deleteImage(lecture.getLectureImg(), "lecture");
                    lectureRepository.deleteById(lecture.getLectureId());

                } else { // 한번이라도 진행했던 강의라면 강의 종강 처리
                    // 강의 종강일 업데이트 & 강의자 null 처리
                    lecture.update(lecture.getContent(), lecture.getStartDate(), LocalDate.now(), lecture.getLectureTime(), null);

                    lectureRepository.save(lecture);
                    // 현재 시간 이후 세션 삭제 처리
                    sessionRepository.deleteAllByLecture_LectureIdAndStartTimeAfter(lecture.getLectureId(), LocalDateTime.now());
                }
                // 개강일이 어제 이전, 종강일이 오늘 이후인 강의
            } else if(lecture.getStartDate().isBefore(LocalDate.now()) && lecture.getEndDate().isAfter(LocalDate.now().minusDays(1))){
                lecture.update(lecture.getContent(), lecture.getStartDate(), LocalDate.now(), lecture.getLectureTime(), null);

                lectureRepository.save(lecture);
                sessionRepository.deleteAllByLecture_LectureIdAndStartTimeAfter(lecture.getLectureId(), LocalDateTime.now());
            }

            // 진행중인 강의: 강의는 종강일을 오늘로 찍고, 세션은 현재 이후 세션만 날림


            logger.info("find lecture");
//            imgUtil.deleteImage(lecture.getLectureImg(), "lecture");
            logger.info("image is deleted");
//            lectureRepository.deleteById(lectureId);
            logger.info("lecture is deleted in service");

            return LectureResponse.LectureId.builder().lectureId(lectureId).build();
        }
        else {
            throw new LectureException.UnvalidLectureId(lectureId);
        }
    }



    // 자신이 가르치고 있는 강의 검색
    @Override
    public List<LectureResponse.LecturePreview> searchMyCurrentTeachingLecture(Integer userId) {
        // 존재하는 회원인 경우
        if(userRepository.existsById(userId)) {
            List<Lecture> searchResult = lectureRepository.findAllByUser_UserIdAndEndDateAfter(userId, LocalDate.now().minusDays(1));
//            List<Lecture> searchResult = lectureRepository.findAllByUser_UserIdAndStartDateBeforeAndEndDateAfter(userId, LocalDate.now().plusDays(1), LocalDate.now().minusDays(1));

            // 개설중인 강의가 없는 경우, 빈 리스트 반환
            List<LectureResponse.LecturePreview> result = new ArrayList<>();
            for (Lecture l: searchResult){
                LectureResponse.LecturePreview lecturePreview = l.toLecturePreview();
                lecturePreview.setSessionId(sessionService.getEarliestAvailableSession(l.getLectureId()));
                result.add(lecturePreview);
            }
            return result;

            // 존재하지 않는 회원인 경우
        } else {
            throw new UserNotFoundException();
        }
    }


    // 수강중인 강의 검색
    @Override
    public List<LectureResponse.LecturePreview> searchMyLecture(Integer userId) throws ParseException {
        List<LectureResponse.LecturePreview> result;
        if(userRepository.existsById(userId)) {
//            List<Lecture> lectures = registrationService.getCurrentLecturesOfUser(userId).getLectureList();
//            for (Lecture l : lectures) {
//                result.add(l.toLecturePreview());
//            }
            result = registrationService.getCurrentLecturePreviewsOfUser(userId).getLecturePreviewList();
            for(LectureResponse.LecturePreview lecturePreview : result) {
                lecturePreview.setSessionId(sessionService.getEarliestAvailableSession(lecturePreview.getLectureId()));
            }
        } else {
            throw new UserNotFoundException();
        }
        return result;
    }


    // 신청 가능한 강의 검색
    @Override
    public List<LectureResponse.LectureDetail> searchAvailableLecture(Integer userId) {

        List<LectureResponse.LectureDetail> result = new ArrayList<>();
        List<Lecture> lectures = lectureRepository.findAllByUser_UserIdNotAndStartDateAfter(userId, LocalDate.now());

        // 수강 신청 가능한 강의가 없을 경우 빈 리스트 return
        for (Lecture l : lectures) {
            // 개강 전이면서 타인이 개설한 강의 중, 수강중이지 않은 강의만 result에 추가
            if(!registrationRepository.findByUserUserIdAndLectureLectureId(userId, l.getLectureId()).isPresent()) {
                result.add(l.toLectureDetail());
            }
        }
        return result;
    }


}
