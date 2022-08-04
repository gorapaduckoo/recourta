package com.ssafy.recourta.domain.session.repository;

import com.ssafy.recourta.domain.session.dto.response.SessionResponse;
import com.ssafy.recourta.domain.session.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    // (start) 시간 이후에 시작하고, (end) 이전에 종료하는 세션 id 조회
    List<SessionResponse.SessionIdMapping> findAllByLecture_LectureIdAndStartTimeAfterAndEndTimeBefore(Integer lectureId, LocalDateTime start, LocalDateTime end);

}
