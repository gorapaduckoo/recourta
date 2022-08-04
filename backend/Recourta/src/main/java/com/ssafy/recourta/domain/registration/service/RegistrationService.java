package com.ssafy.recourta.domain.registration.service;

import com.ssafy.recourta.domain.registration.dto.response.RegistrationResponse;

import java.text.ParseException;

public interface RegistrationService {

    RegistrationResponse.LectureList getLecturesOfUser(Integer userId);
    RegistrationResponse.UserList getUsersOfLecture(Integer lectureId);
    RegistrationResponse.LectureList getCurrentLecturesOfUser(Integer userId) throws ParseException;
}
