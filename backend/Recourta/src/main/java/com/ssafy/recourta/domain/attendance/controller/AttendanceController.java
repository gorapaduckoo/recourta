package com.ssafy.recourta.domain.attendance.controller;

import com.ssafy.recourta.domain.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/auto/{sessionId}")
    public ResponseEntity<String> recordAllAttendanceOfSession(@PathVariable("sessionId") Integer sessionId) {
        try {
            attendanceService.recordAllAttendanceOfSession(sessionId);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
