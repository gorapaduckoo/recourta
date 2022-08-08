package com.ssafy.recourta.domain.notice.controller;

import com.ssafy.recourta.domain.notice.dto.request.NoticeRequest;
import com.ssafy.recourta.domain.notice.dto.response.NoticeResponse;
import com.ssafy.recourta.domain.notice.entity.Notice;
import com.ssafy.recourta.domain.notice.service.NoticeService;
import com.ssafy.recourta.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private Long DEFAULT_TIMEOUT = 60L * 1000L * 60L;

    // 현재 생성된 SseEmitter들의 집합
    static Set<SseEmitter> emitterRepository = new HashSet<>();

    @Autowired
    private NoticeService noticeService;

    @PostMapping
    public ResponseEntity<NoticeResponse.NoticeCount> createNotice(@Valid @RequestBody NoticeRequest.NoticeCreateForm notice) throws Exception {
        NoticeResponse.NoticeCount result = noticeService.createNotice(notice);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    public SseEmitter subscribe(@AuthenticationPrincipal User user) throws IOException {

        return noticeService.subscribe(user.getUserId());
    }

    @GetMapping
    public ResponseEntity<List<NoticeResponse.NoticeTitle>> searchNoticeListByUserId(@Valid @RequestParam Integer userId) throws Exception {
        List<NoticeResponse.NoticeTitle> result = noticeService.searchByUserId(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeResponse.NoticeDetail> searchNoticeByNoticeId(@PathVariable Integer noticeId) throws Exception {
        NoticeResponse.NoticeDetail result = noticeService.searchByNoticeId(noticeId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Async
    @EventListener
    public void catchEvent(Notice newNotice) {
        List<SseEmitter> deadEmitters = new ArrayList<>();

        deadEmitters.forEach(e -> {
            try {
                SseEmitter.SseEventBuilder eb = SseEmitter.event().name("newNotice").data(newNotice, MediaType.APPLICATION_JSON);
                e.send(eb);
            } catch (IOException ex) {
                deadEmitters.add(e);
            }
        });

        emitterRepository.removeAll(deadEmitters);
    }
}
