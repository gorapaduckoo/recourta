package com.ssafy.recourta.domain.session.service;

import com.ssafy.recourta.domain.session.dto.request.SessionRequest;
import com.ssafy.recourta.domain.session.dto.response.SessionResponse;

import java.util.List;

public interface SessionService {

    List<SessionResponse.SessionId> createSession(List<SessionRequest.SessionCreateForm> sessions, Integer lectureId) throws Exception;
}
