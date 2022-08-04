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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping
    public ResponseEntity<NoticeResponse.NoticeCount> createNotice(@RequestBody NoticeRequest.NoticeCreateForm notice) throws Exception {
        NoticeResponse.NoticeCount result = noticeService.createNotice(notice);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<NoticeResponse.NoticeTitle>> searchNoticeListByUserId(@PathVariable Integer userId) throws Exception {
        List<NoticeResponse.NoticeTitle> result = noticeService.searchByUserId(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeResponse.NoticeDetail> searchNoticeByNoticeId(@PathVariable Integer noticeId) throws Exception {
        NoticeResponse.NoticeDetail result = noticeService.searchByNoticeId(noticeId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
