package com.ssafy.recourta.domain.checkinout.respository;

import com.ssafy.recourta.domain.attendance.entity.Attendance;
import com.ssafy.recourta.domain.checkinout.entity.CheckInOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckInOutRepository extends JpaRepository<CheckInOut, Long> {
    // 수업의 한 수강생의 모든 입퇴실 기록
    List<CheckInOut> findAllByUserUserIdAndSessionSessionId(Integer userId, Integer sessionId);
    // 가장 빠른 입실 기록
    CheckInOut findFirstByUserUserIdAndSessionSessionIdOrderByEnterTimeAsc(Integer userId, Integer sessionId);
}
