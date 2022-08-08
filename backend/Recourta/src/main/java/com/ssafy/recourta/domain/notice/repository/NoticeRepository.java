package com.ssafy.recourta.domain.notice.repository;

import com.ssafy.recourta.domain.notice.dto.response.NoticeResponse;
import com.ssafy.recourta.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    List<Notice> findAllByUser_UserId(Integer userId);
    List<Notice> findAllByLecture_LectureId(Integer lectureId);

    List<Notice> findAllByUser_UserIdAndIsRead(Integer userId, Boolean isRead);
}
