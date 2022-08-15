package com.ssafy.recourta.domain.registration.service;

import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;
import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import com.ssafy.recourta.domain.registration.dto.request.RegistrationRequest;
import com.ssafy.recourta.domain.registration.dto.response.RegistrationResponse;
import com.ssafy.recourta.domain.registration.entity.Registration;
import com.ssafy.recourta.domain.registration.repository.RegistrationRepository;
import com.ssafy.recourta.domain.user.dto.response.UserResponse;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
import com.ssafy.recourta.global.exception.LectureException;
import com.ssafy.recourta.global.exception.RegistrationException;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public RegistrationResponse.LectureList getLecturesOfUser(Integer userId) {
        List<Registration> registrationList = registrationRepository.findAllByUserUserId(userId);
        List<LectureResponse.LecturePreview> lectureList = new ArrayList<>();

        for(Registration registration : registrationList) {
            Lecture lecture = lectureRepository.findById(registration.getLecture().getLectureId()).orElseThrow(() -> new IllegalArgumentException());
            lectureList.add(lecture.toLecturePreview());
        }

        return RegistrationResponse.LectureList.builder().lectureList(lectureList).build();
    }

    @Override
    public RegistrationResponse.UserList getUsersOfLecture(Integer lectureId) {
        List<Registration> registrationList = registrationRepository.findAllByLectureLectureId(lectureId);
        List<UserResponse.Search> userList = new ArrayList<>();

        for(Registration registration : registrationList) {
            User user = userRepository.findById(registration.getUser().getUserId()).orElseThrow(() -> new IllegalArgumentException());
            userList.add(UserResponse.Search.build(user));
        }

        return RegistrationResponse.UserList.builder().userList(userList).build();
    }

    @Override
    public RegistrationResponse.LectureList getCurrentLecturesOfUser(Integer userId) {
        List<Registration> registrationList = registrationRepository.findAllByUserUserId(userId);
        List<LectureResponse.LecturePreview> currentLectureList = new ArrayList<>();

//        SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd");
//        Date now = Date.valueOf(sdformat.format(new Date(System.currentTimeMillis())));
        for(Registration registration : registrationList) {
            System.out.println(registration.getLecture().getLectureId());
            Lecture lecture = lectureRepository.findById(registration.getLecture().getLectureId()).orElseThrow(() -> new IllegalArgumentException());
            LocalDate startDate = lecture.getStartDate();
            LocalDate endDate = lecture.getEndDate();
            if((startDate.isBefore(LocalDate.now()) || startDate.equals(LocalDate.now())) && (endDate.isAfter(LocalDate.now()) || endDate.equals(LocalDate.now()))) currentLectureList.add(lecture.toLecturePreview());
        }

        return RegistrationResponse.LectureList.builder().lectureList(currentLectureList).build();
    }

    @Override
    public RegistrationResponse.LectureList getPreviousLecturesOfUser(Integer userId) {
        List<Registration> registrationList = registrationRepository.findAllByUserUserId(userId);
        List<LectureResponse.LecturePreview> previousLectureList = new ArrayList<>();

        for(Registration registration : registrationList) {
            System.out.println(registration.getLecture().getLectureId());
            Lecture lecture = lectureRepository.findById(registration.getLecture().getLectureId()).orElseThrow(() -> new IllegalArgumentException());
            LocalDate startDate = lecture.getStartDate();
            LocalDate endDate = lecture.getEndDate();
            if(startDate.isBefore(LocalDate.now()) && endDate.isBefore(LocalDate.now())) previousLectureList.add(lecture.toLecturePreview());
        }

        return RegistrationResponse.LectureList.builder().lectureList(previousLectureList).build();
    }

    @Override
    public RegistrationResponse.RegistrationId registerLecture(RegistrationRequest.RegistrationInfo registrationInfo) {
        User user = userRepository.findByUserId(registrationInfo.getUserId()).orElseThrow(UserNotFoundException::new);
        Lecture lecture = lectureRepository.findById(registrationInfo.getLectureId()).orElseThrow(() -> new LectureException.UnvalidLectureId(registrationInfo.getLectureId()));
        Registration registration = registrationRepository.findByUserUserIdAndLectureLectureId(user.getUserId(), lecture.getLectureId()).orElse(null);

        if(registration != null) throw new RegistrationException.RegistrationAlreadyExists();
        registration = registrationInfo.toEntity(user, lecture);
        try {
            Registration result = registrationRepository.save(registration);
            return RegistrationResponse.RegistrationId.builder().registrationId(result.getId()).build();
        } catch(Exception e) {
            throw new RegistrationException.RegistrationFail();
        }
    }

    @Override
    @Transactional
    public RegistrationResponse.RegistrationId withdrawLecture(RegistrationRequest.RegistrationInfo registrationInfo) {
        User user = userRepository.findByUserId(registrationInfo.getUserId()).orElseThrow(UserNotFoundException::new);
        Lecture lecture = lectureRepository.findById(registrationInfo.getLectureId()).orElseThrow(() -> new LectureException.UnvalidLectureId(registrationInfo.getLectureId()));
        Registration registration = registrationRepository.findByUserUserIdAndLectureLectureId(user.getUserId(), lecture.getLectureId()).orElseThrow(() -> new RegistrationException.RegistrationDoesNotExist());

        try {
            registrationRepository.deleteByUserUserIdAndLectureLectureId(registration.getUser().getUserId(), registration.getLecture().getLectureId());
            return RegistrationResponse.RegistrationId.builder().registrationId(registration.getId()).build();
        } catch(Exception e) {
            e.printStackTrace();
            throw new RegistrationException.RegistrationDeleteFail();
        }
    }


}
