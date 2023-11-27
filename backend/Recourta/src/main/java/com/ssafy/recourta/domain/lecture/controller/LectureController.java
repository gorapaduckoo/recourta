package com.ssafy.recourta.domain.lecture.controller;

import com.ssafy.recourta.domain.lecture.dto.request.LectureCreateRequest;
import com.ssafy.recourta.domain.lecture.dto.request.LectureUpdateRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureDetailResponse;
import com.ssafy.recourta.domain.lecture.dto.response.LectureIdResponse;
import com.ssafy.recourta.domain.lecture.dto.response.LecturePreviewResponse;
import com.ssafy.recourta.domain.lecture.service.LectureService;
import com.ssafy.recourta.domain.session.dto.request.SessionRequest;
import com.ssafy.recourta.domain.session.service.SessionService;
import com.ssafy.recourta.global.exception.LectureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<LectureIdResponse> createLecture(@Valid @RequestPart("request") LectureCreateRequest lecture, @RequestPart(value="lectureImg", required = false)MultipartFile lectureImg) throws Exception {
        LectureIdResponse result = LectureIdResponse.builder().build();
        try {
            result = lectureService.createLecture(lecture, lectureImg);
            Integer sessionResult = sessionService.createSession(lecture.getLectureTime(), result.getLectureId(), false);
            if (sessionResult <= 0) {
                throw new LectureException.SessionSaveFail(result.getLectureId());
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            lectureService.deleteLecture(result.getLectureId());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureDetailResponse> searchByLectureId(@PathVariable String lectureId) throws Exception {
        Integer id = Integer.parseInt(lectureId);
        LectureDetailResponse result = lectureService.searchByLectureId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{lectureId}")
    public ResponseEntity<LectureIdResponse> updateLecture(@PathVariable Integer lectureId, @Valid @RequestPart("request") LectureUpdateRequest input, @RequestPart(value="lectureImg", required = false) MultipartFile lectureImg) throws Exception {
        LectureIdResponse result = lectureService.updateLecture(lectureId, input, lectureImg);

        List<SessionRequest.SessionCreateForm> newLectureTimes = input.getLectureTime();
        sessionService.changeSession(newLectureTimes,lectureId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value="/{lectureId}")
    public ResponseEntity<LectureIdResponse> deleteLecture(@PathVariable Integer lectureId) throws Exception {
       
        LectureIdResponse result = lectureService.deleteLecture(lectureId);
       
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{userId}/currentTeachingList")
    public ResponseEntity<List<LecturePreviewResponse>> searchMyCurrentTeachingLecture(@PathVariable Integer userId) throws Exception {
        List<LecturePreviewResponse> result = lectureService.searchMyCurrentTeachingLecture(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{userId}/currentLectureList")
    public ResponseEntity<List<LecturePreviewResponse>> searchMyCurrentLecture(@PathVariable Integer userId) throws ParseException {
        List<LecturePreviewResponse> result = lectureService.searchMyLecture(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{userId}/availableList")
    public ResponseEntity<List<LectureDetailResponse>> searchAllLecture(@PathVariable Integer userId) throws Exception {
        List<LectureDetailResponse> result = lectureService.searchAvailableLecture(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
