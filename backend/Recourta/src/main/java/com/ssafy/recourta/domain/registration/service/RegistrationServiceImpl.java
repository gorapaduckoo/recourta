package com.ssafy.recourta.domain.registration.service;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import com.ssafy.recourta.domain.registration.dto.response.RegistrationResponse;
import com.ssafy.recourta.domain.registration.entity.Registration;
import com.ssafy.recourta.domain.registration.repository.RegistrationRepository;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        List<Registration> registrationList = registrationRepository.findByUserUserId(userId);
        List<Lecture> lectureList = new ArrayList<>();

        for(Registration registration : registrationList) {
            Lecture lecture = lectureRepository.findById(registration.getLecture().getLectureId()).orElseThrow(() -> new IllegalArgumentException());
            lectureList.add(lecture);
        }

        return RegistrationResponse.LectureList.builder().lectureList(lectureList).build();
    }

    @Override
    public RegistrationResponse.UserList getUsersOfLecture(Integer lectureId) {
        List<Registration> registrationList = registrationRepository.findByLectureLectureId(lectureId);
        List<User> userList = new ArrayList<>();

        for(Registration registration : registrationList) {
            User user = userRepository.findById(registration.getUser().getUserId()).orElseThrow(() -> new IllegalArgumentException());
            userList.add(user);
        }

        return RegistrationResponse.UserList.builder().userList(userList).build();
    }

    @Override
    public RegistrationResponse.LectureList getCurrentLecturesOfUser(Integer userId) throws ParseException {
        List<Registration> registrationList = registrationRepository.findByUserUserId(userId);
        List<Lecture> currentLectureList = new ArrayList<>();

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd");
        Date now = Date.valueOf(sdformat.format(new Date(System.currentTimeMillis())));
        for(Registration registration : registrationList) {
            Lecture lecture = lectureRepository.findById(registration.getLecture().getLectureId()).orElseThrow(() -> new IllegalArgumentException());
            Date startDate = lecture.getStartDate();
            Date endDate = lecture.getEndDate();
            if((startDate.before(now) || startDate.equals(now)) && (endDate.after(now) || endDate.equals(now))) currentLectureList.add(lecture);
        }

        return RegistrationResponse.LectureList.builder().lectureList(currentLectureList).build();
    }
}
