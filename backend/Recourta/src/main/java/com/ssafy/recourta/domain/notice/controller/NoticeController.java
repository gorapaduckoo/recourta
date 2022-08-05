package com.ssafy.recourta.domain.notice.controller;

import com.ssafy.recourta.domain.notice.dto.request.NoticeRequest;
import com.ssafy.recourta.domain.notice.dto.response.NoticeResponse;
import com.ssafy.recourta.domain.notice.repository.NoticeRepository;
import com.ssafy.recourta.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping
    public ResponseEntity<NoticeResponse.NoticeCount> createNotice(@Valid @RequestBody NoticeRequest.NoticeCreateForm notice) throws Exception {
        NoticeResponse.NoticeCount result = noticeService.createNotice(notice);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping(value = "/subscribe", produces = "text/event-stream")
//    public SseEmitter

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
}
