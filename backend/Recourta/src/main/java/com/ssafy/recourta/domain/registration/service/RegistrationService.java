package com.ssafy.recourta.domain.registration.service;

import com.ssafy.recourta.domain.registration.dto.request.RegistrationRequest;
import com.ssafy.recourta.domain.registration.dto.response.RegistrationResponse;

import java.text.ParseException;

public interface RegistrationService {

    RegistrationResponse.LecturePreviewList getLecturesOfUser(Integer userId);
    RegistrationResponse.UserList getUsersOfLecture(Integer lectureId);
    RegistrationResponse.LectureDetailList getCurrentLectureDetailsOfUser(Integer userId) throws ParseException;
    RegistrationResponse.LecturePreviewList getCurrentLecturePreviewsOfUser(Integer userId) throws ParseException;
    RegistrationResponse.LectureDetailList getPreviousLecturesOfUser(Integer userId) throws ParseException;
    RegistrationResponse.RegistrationId registerLecture(RegistrationRequest.RegistrationInfo registrationInfo);
    RegistrationResponse.RegistrationId withdrawLecture(RegistrationRequest.RegistrationInfo registrationInfo);

}
