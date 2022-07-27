package com.ssafy.recourta.domain.lecture.controller;

import com.ssafy.recourta.domain.lecture.dto.request.LectureRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;
import com.ssafy.recourta.domain.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {
    private Logger logger = LoggerFactory.getLogger(LectureController.class);

    @Autowired
    private LectureService lectureService;

    @PostMapping
    public ResponseEntity<LectureResponse.LectureId> createLecture(@RequestBody LectureRequest.LectureCreateForm lecture) throws Exception {
        LectureResponse.LectureId result = lectureService.createLecture(lecture);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureResponse.LectureDetail> searchByLectureId(@PathVariable String lectureId) throws Exception {
        Integer id = Integer.parseInt(lectureId);
        LectureResponse.LectureDetail result = lectureService.searchByLectureId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{lectureId}")
    public ResponseEntity<LectureResponse.LectureId> updateLecture(@PathVariable String lectureId, @RequestBody LectureRequest.LectureUpdateForm input) throws Exception {
        Integer id = Integer.parseInt(lectureId);
        LectureResponse.LectureId result = lectureService.updateLecture(id, input);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{lectureId}")
    public ResponseEntity<LectureResponse.LectureId> deleteLecture(@PathVariable String lectureId) throws Exception {
        Integer id = Integer.parseInt(lectureId);
        LectureResponse.LectureId result = lectureService.deleteLecture(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
