package com.ssafy.recourta.domain.registration.service;

import com.ssafy.recourta.domain.registration.dto.request.RegistrationRequest;
import com.ssafy.recourta.domain.registration.dto.response.RegistrationResponse;

import java.text.ParseException;

public interface RegistrationService {

    RegistrationResponse.LectureList getLecturesOfUser(Integer userId);
    RegistrationResponse.UserList getUsersOfLecture(Integer lectureId);
    RegistrationResponse.LectureList getCurrentLecturesOfUser(Integer userId) throws ParseException;
    RegistrationResponse.LectureList getPreviousLecturesOfUser(Integer userId) throws ParseException;
    RegistrationResponse.RegistrationId registerLecture(RegistrationRequest.RegistrationInfo registrationInfo);
    RegistrationResponse.RegistrationId withdrawLecture(RegistrationRequest.RegistrationInfo registrationInfo);

}
