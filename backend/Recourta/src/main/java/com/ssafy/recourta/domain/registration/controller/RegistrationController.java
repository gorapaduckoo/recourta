package com.ssafy.recourta.domain.registration.controller;

import com.ssafy.recourta.domain.registration.dto.response.RegistrationResponse;
import com.ssafy.recourta.domain.registration.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<RegistrationResponse.LectureList> getLecturesOfUser(@PathVariable String userId) {
        Integer id = Integer.parseInt(userId);
        RegistrationResponse.LectureList result = registrationService.getLecturesOfUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/lecture/{lectureId}")
    public ResponseEntity<RegistrationResponse.UserList> getUsersOfLecture(@PathVariable String lectureId) {
        Integer id = Integer.parseInt(lectureId);
        RegistrationResponse.UserList result = registrationService.getUsersOfLecture(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
