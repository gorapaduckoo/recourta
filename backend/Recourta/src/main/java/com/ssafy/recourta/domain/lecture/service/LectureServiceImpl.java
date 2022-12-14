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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
        // RequestDto??? ?????? userId??? user ?????? ??????
        User user = userRepository.findById(input.getUserId()).orElseThrow(UserNotFoundException::new);

        // ????????? user ????????? ????????? lecture ????????? ?????????
        Lecture lecture = input.toEntity(user);
        imgUtil.uploadImage(lecture, lectureImg);

        // DB ?????? ??? ????????? lecture??? lectureId??? ??????????????? ??????
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

      
        if(lectureRepository.existsById(lectureId)) {

           

            Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                    ()-> new LectureException.UnvalidLectureId(lectureId));
            
            // ?????? ?????? ????????? ????????? ??? ??? ?????? ??????

            // ???????????? ?????? ????????? ??????
            if(lecture.getStartDate().isAfter(LocalDate.now().minusDays(1))) {
                Integer cnt = sessionRepository.countByLecture_LectureIdAndStartTimeBefore(lecture.getLectureId(), LocalDateTime.now());
                if (cnt == 0) { // ?????? ?????? ???????????? ?????? ???????????? ?????? ?????? ??????
                    sessionRepository.deleteAllByLecture_LectureIdAndStartTimeAfter(lectureId, LocalDateTime.now());
                    imgUtil.deleteImage(lecture.getLectureImg(), "lecture");
                    lectureRepository.deleteById(lecture.getLectureId());

                } else { // ??????????????? ???????????? ???????????? ?????? ?????? ??????
                    // ?????? ????????? ???????????? & ????????? null ??????
                    lecture.update(lecture.getContent(), lecture.getStartDate(), LocalDate.now(), lecture.getLectureTime(), null);

                    lectureRepository.save(lecture);
                    // ?????? ?????? ?????? ?????? ?????? ??????
                    sessionRepository.deleteAllByLecture_LectureIdAndStartTimeAfter(lecture.getLectureId(), LocalDateTime.now());
                }
                // ???????????? ?????? ??????, ???????????? ?????? ????????? ??????
            } else if(lecture.getStartDate().isBefore(LocalDate.now()) && lecture.getEndDate().isAfter(LocalDate.now().minusDays(1))){
                lecture.update(lecture.getContent(), lecture.getStartDate(), LocalDate.now(), lecture.getLectureTime(), null);

                lectureRepository.save(lecture);
                sessionRepository.deleteAllByLecture_LectureIdAndStartTimeAfter(lecture.getLectureId(), LocalDateTime.now());
            }

            // ???????????? ??????: ????????? ???????????? ????????? ??????, ????????? ?????? ?????? ????????? ??????



         

            return LectureResponse.LectureId.builder().lectureId(lectureId).build();
        }
        else {
            throw new LectureException.UnvalidLectureId(lectureId);
        }
    }



    // ????????? ???????????? ?????? ?????? ??????
    @Override
    public List<LectureResponse.LecturePreview> searchMyCurrentTeachingLecture(Integer userId) {
        // ???????????? ????????? ??????
        if(userRepository.existsById(userId)) {
            List<Lecture> searchResult = lectureRepository.findAllByUser_UserIdAndEndDateAfter(userId, LocalDate.now().minusDays(1));
//            List<Lecture> searchResult = lectureRepository.findAllByUser_UserIdAndStartDateBeforeAndEndDateAfter(userId, LocalDate.now().plusDays(1), LocalDate.now().minusDays(1));

            // ???????????? ????????? ?????? ??????, ??? ????????? ??????
            List<LectureResponse.LecturePreview> result = new ArrayList<>();
            for (Lecture l: searchResult){
                LectureResponse.LecturePreview lecturePreview = l.toLecturePreview();
                lecturePreview.setSessionId(sessionService.getEarliestAvailableSession(l.getLectureId()));
                result.add(lecturePreview);
            }
            return result;

            // ???????????? ?????? ????????? ??????
        } else {
            throw new UserNotFoundException();
        }
    }


    // ???????????? ?????? ??????
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


    // ?????? ????????? ?????? ??????
    @Override
    public List<LectureResponse.LectureDetail> searchAvailableLecture(Integer userId) {

        List<LectureResponse.LectureDetail> result = new ArrayList<>();
        List<Lecture> lectures = lectureRepository.findAllByUser_UserIdNotAndStartDateAfter(userId, LocalDate.now());

        // ?????? ?????? ????????? ????????? ?????? ?????? ??? ????????? return
        for (Lecture l : lectures) {
            // ?????? ???????????? ????????? ????????? ?????? ???, ??????????????? ?????? ????????? result??? ??????
            if(!registrationRepository.findByUserUserIdAndLectureLectureId(userId, l.getLectureId()).isPresent()) {
                result.add(l.toLectureDetail());
            }
        }
        return result;
    }


}
