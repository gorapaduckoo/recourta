package com.ssafy.recourta.domain.registration.controller;

import com.ssafy.recourta.domain.registration.dto.request.RegistrationRequest;
import com.ssafy.recourta.domain.registration.dto.response.RegistrationResponse;
import com.ssafy.recourta.domain.registration.entity.Registration;
import com.ssafy.recourta.domain.registration.service.RegistrationService;
import com.ssafy.recourta.global.exception.LectureException;
import com.ssafy.recourta.global.exception.RegistrationException;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

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

    @GetMapping("/user/current/{userId}")
    public ResponseEntity<RegistrationResponse.LectureList> getCurrentLecturesOfUser(@PathVariable String userId) throws ParseException {
        Integer id = Integer.parseInt(userId);
        RegistrationResponse.LectureList result = registrationService.getCurrentLecturesOfUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user/previous/{userId}")
    public ResponseEntity<RegistrationResponse.LectureList> getPreviousLecturesOfUser(@PathVariable String userId) throws ParseException {
        Integer id = Integer.parseInt(userId);
        RegistrationResponse.LectureList result = registrationService.getPreviousLecturesOfUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<RegistrationResponse.RegistrationId> registerToLecture(@RequestBody RegistrationRequest.RegistrationInfo request) {
        try {
            RegistrationResponse.RegistrationId result = registrationService.registerLecture(request);
            return ResponseEntity.ok().body(result);
        } catch(RegistrationException.RegistrationFail e) {
            return ResponseEntity.internalServerError().build();
        } catch(UserNotFoundException e) {
            return ResponseEntity.internalServerError().build();
        } catch(LectureException.UnvalidLectureId e) {
            return ResponseEntity.internalServerError().build();
        } catch(RegistrationException.RegistrationAlreadyExists e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<RegistrationResponse.RegistrationId> withdrawRegistration(@RequestBody RegistrationRequest.RegistrationInfo request) {
        try {
            RegistrationResponse.RegistrationId result = registrationService.withdrawLecture(request);
            return ResponseEntity.ok().body(result);
        } catch(RegistrationException.RegistrationDeleteFail e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch(UserNotFoundException e) {
            return ResponseEntity.internalServerError().build();
        } catch(LectureException.UnvalidLectureId e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
