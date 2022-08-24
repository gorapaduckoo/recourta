package com.ssafy.recourta.domain.attendance.controller;

import com.ssafy.recourta.domain.attendance.dto.request.AttendanceRequest;
import com.ssafy.recourta.domain.attendance.dto.response.AttendanceResponse;
import com.ssafy.recourta.domain.attendance.service.AttendanceService;
import com.ssafy.recourta.global.exception.AttendanceException;
import com.ssafy.recourta.global.exception.SessionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/autorecord/{sessionId}")
    public ResponseEntity<String> recordAllAttendanceOfSession(@PathVariable("sessionId") Integer sessionId) {
        try {
            attendanceService.recordAllAttendanceOfSession(sessionId);
            return ResponseEntity.ok().build();
        } catch(SessionNotFoundException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        } catch(AttendanceException.AlreadyAttendanceRecordedSessionException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<AttendanceResponse.SessionAttendance> getAttendanceOfSession(@PathVariable("sessionId") Integer sessionId) {
        AttendanceResponse.SessionAttendance sessionAttendance = attendanceService.getAttendanceOfSession(sessionId);
        return ResponseEntity.ok().body(sessionAttendance);
    }

    @GetMapping("/lecture/{lectureId}")
    public ResponseEntity<AttendanceResponse.LectureAttendance> getAttendanceOfLecture(@PathVariable("lectureId") Integer lectureId) {
        AttendanceResponse.LectureAttendance lectureAttendance = attendanceService.getAttendanceOfLecture(lectureId);
        return ResponseEntity.ok().body(lectureAttendance);
    }

    @PutMapping("/change")
    public ResponseEntity<String> changeAttendance(@RequestBody AttendanceRequest.AttendanceInfo request) {
        try {
            attendanceService.changeAttendance(request);
            return ResponseEntity.ok().body("success");
        } catch(AttendanceException.NoSuchAttendanceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch(AttendanceException.InvalidTypeOfAttendanceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
