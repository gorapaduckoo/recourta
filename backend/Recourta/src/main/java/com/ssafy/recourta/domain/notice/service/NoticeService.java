package com.ssafy.recourta.domain.notice.service;

import com.ssafy.recourta.domain.notice.dto.request.NoticeRequest;
import com.ssafy.recourta.domain.notice.dto.response.NoticeResponse;
import com.ssafy.recourta.domain.notice.entity.Notice;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

public interface NoticeService {

    NoticeResponse.NoticeCount createNotice(NoticeRequest.NoticeCreateForm notice) throws Exception;
    List<NoticeResponse.NoticeTitle> searchByUserId(Integer userId) throws Exception;
    List<NoticeResponse.NoticeTitle> searchByLectureId(Integer lectureId) throws Exception;
    NoticeResponse.NoticeDetail searchByNoticeId(Integer noticeId) throws Exception;
    SseEmitter subscribe(Integer userId) throws IOException;

    void emitEvent(Integer userId, Notice notice) throws Exception;
}
