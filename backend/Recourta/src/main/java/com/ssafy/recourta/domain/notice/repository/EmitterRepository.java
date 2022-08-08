package com.ssafy.recourta.domain.notice.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EmitterRepository {

    SseEmitter save(Integer userId, SseEmitter sseEmitter);

    SseEmitter findSseEmitterByUserId(Integer userId) throws Exception;


    void deleteEmitterByUserId(Integer userId);
}
