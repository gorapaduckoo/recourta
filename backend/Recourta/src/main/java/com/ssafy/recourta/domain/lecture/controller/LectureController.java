package com.ssafy.recourta.domain.lecture.controller;

import com.ssafy.recourta.domain.lecture.dto.request.LectureCreateRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureCreateResponse;
import com.ssafy.recourta.domain.lecture.dto.response.LectureReadResponse;
import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import com.ssafy.recourta.domain.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {
    private Logger logger = LoggerFactory.getLogger(LectureController.class);

    @Autowired
    private LectureService lectureService;

    @PostMapping
    public ResponseEntity<LectureCreateResponse> createLecture(LectureCreateRequest lecture) {
        lectureService.createLecture(lecture);
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureReadResponse> searchByLectureId(@PathVariable String lectureId) throws Exception {
        Integer lectureid = Integer.parseInt(lectureId);
        LectureReadResponse result = lectureService.findByLectureId(lectureid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
