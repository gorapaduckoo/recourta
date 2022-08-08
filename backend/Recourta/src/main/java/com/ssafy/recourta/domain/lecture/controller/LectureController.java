package com.ssafy.recourta.domain.lecture.controller;

import com.ssafy.recourta.domain.lecture.dto.request.LectureRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;
import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.service.LectureService;
import com.ssafy.recourta.domain.session.dto.request.SessionRequest;
import com.ssafy.recourta.domain.session.dto.response.SessionResponse;
import com.ssafy.recourta.domain.session.entity.Session;
import com.ssafy.recourta.domain.session.service.SessionService;
import com.ssafy.recourta.global.exception.LectureException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {
    private static final Logger logger = LoggerFactory.getLogger(LectureController.class);

    @Autowired
    private LectureService lectureService;

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<LectureResponse.LectureId> createLecture(@Valid @RequestBody LectureRequest.LectureCreateForm lecture) throws Exception {
        LectureResponse.LectureId result = lectureService.createLecture(lecture);
        Integer sessionResult = sessionService.createSession(lecture.getLectureTime(), result.getLectureId(), false);
        if(sessionResult <=0 ){
            throw new LectureException.SessionSaveFail(result.getLectureId());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureResponse.LectureDetail> searchByLectureId(@PathVariable String lectureId) throws Exception {
        Integer id = Integer.parseInt(lectureId);
        LectureResponse.LectureDetail result = lectureService.searchByLectureId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{lectureId}")
    public ResponseEntity<LectureResponse.LectureId> updateLecture(@PathVariable String lectureId,@Valid @RequestBody LectureRequest.LectureUpdateForm input) throws Exception {
        Integer id = Integer.parseInt(lectureId);
        LectureResponse.LectureId result = lectureService.updateLecture(id, input);

        List<SessionRequest.SessionCreateForm> newLectureTimes = input.getLectureTime();
        sessionService.changeSession(newLectureTimes,id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{lectureId}")
    public ResponseEntity<LectureResponse.LectureId> deleteLecture(@PathVariable String lectureId) throws Exception {
        Integer id = Integer.parseInt(lectureId);
        LectureResponse.LectureId result = lectureService.deleteLecture(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{userId}/currentTeachingList")
    public ResponseEntity<List<LectureResponse.LecturePreview>> searchMyCurrentTeachingLecture(@PathVariable String userId) throws Exception {
        Integer id = Integer.parseInt(userId);
        List<LectureResponse.LecturePreview> result = lectureService.searchMyCurrentTeachingLecture(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
