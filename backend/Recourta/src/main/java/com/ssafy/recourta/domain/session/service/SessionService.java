package com.ssafy.recourta.domain.session.service;

import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;
import com.ssafy.recourta.domain.session.dto.request.SessionRequest;
import com.ssafy.recourta.domain.session.dto.response.SessionResponse;
import com.ssafy.recourta.domain.session.entity.Session;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDate;
import java.util.List;

public interface SessionService {

    Integer createSession(List<SessionRequest.SessionCreateForm> sessions, Integer lectureId, boolean isUpdate) throws Exception;
    List<SessionResponse.SessionIdMapping> searchSessionByDateAndLectureId(LocalDate startDate, LocalDate endDate, Integer lectureId) throws Exception;
    Integer changeSession(List<SessionRequest.SessionCreateForm> sessions, Integer lectureId) throws Exception;
    SessionResponse.SessionId deleteSession(Integer sessionId);
}
