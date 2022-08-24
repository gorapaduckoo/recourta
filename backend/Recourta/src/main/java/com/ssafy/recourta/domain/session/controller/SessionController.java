package com.ssafy.recourta.domain.session.controller;

import com.ssafy.recourta.domain.session.dto.response.SessionResponse;
import com.ssafy.recourta.domain.session.service.SessionService;
import com.ssafy.recourta.global.exception.SessionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/starttime/{sessionId}")
    public ResponseEntity<? extends Object> getSessionStartTime(@PathVariable("sessionId") Integer sessionId) {
        try {
            SessionResponse.SessionStartTime sessionStartTime = sessionService.getSessionStartTime(sessionId);
            return ResponseEntity.ok().body(sessionStartTime);
        } catch (SessionNotFoundException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
