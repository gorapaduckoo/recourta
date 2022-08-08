package com.ssafy.recourta.domain.notice.repository;

import com.ssafy.recourta.global.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EmitterRepositoryImpl implements EmitterRepository{

    private final Map<Integer, SseEmitter> emitters = new ConcurrentHashMap<>();


    @Override
    public SseEmitter save(Integer userId, SseEmitter sseEmitter) {
        emitters.put(userId, sseEmitter);
        System.out.println(userId+"의 emitter 생성 완료!");
        return sseEmitter;
    }




    @Override
    public SseEmitter findSseEmitterByUserId(Integer userId) throws Exception {
        if (emitters.containsKey(userId)){
            return emitters.get(userId);
        } else {
            throw new Exception(userId + "에 해당하는 emitter가 없습니다");
        }
    }


    @Override
    public void deleteEmitterByUserId(Integer userId) {
        emitters.remove(userId);
    }

}
