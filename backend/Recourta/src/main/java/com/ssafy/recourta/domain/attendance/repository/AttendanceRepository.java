package com.ssafy.recourta.domain.attendance.repository;

import com.ssafy.recourta.domain.attendance.entity.Attendance;
import com.ssafy.recourta.domain.session.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    // 수업 시간의 모든 출석 정보
    List<Attendance> findAllBySessionSessionId(Integer sessionId);
    Optional<Attendance> findByUserUserIdAndSessionSessionId(Integer userId, Integer sessionId);
}
