package com.ssafy.recourta.domain.notice.service;

import com.ssafy.recourta.domain.notice.dto.request.NoticeRequest;
import com.ssafy.recourta.domain.notice.dto.response.NoticeResponse;

import java.util.List;

public interface NoticeService {

    NoticeResponse.NoticeCount createNotice(NoticeRequest.NoticeCreateForm notice) throws Exception;
    List<NoticeResponse.NoticeTitle> searchByUserId(Integer userId) throws Exception;
    List<NoticeResponse.NoticeTitle> searchByLectureId(Integer lectureId) throws Exception;

    NoticeResponse.NoticeDetail searchByNoticeId(Integer noticeId) throws Exception;
}
