package com.ssafy.recourta.domain.attendance.service;

import com.ssafy.recourta.domain.attendance.dto.request.AttendanceRequest;
import com.ssafy.recourta.domain.attendance.dto.response.AttendanceResponse;
import com.ssafy.recourta.domain.session.entity.Session;

import java.util.Optional;

public interface AttendanceService {
    // 해당 수업의 출석정보 자동 반영
    void recordAllAttendanceOfSession(Integer sessionId);
    // 해당 수업의 모든 출석 여부 조회
    AttendanceResponse.SessionAttendance getAttendanceOfSession(Integer sessionId);
    // 해당 강의의 모든 출석 여부 조회
    AttendanceResponse.LectureAttendance getAttendanceOfLecture(Integer lectureId);
    // 특정 수업, 특정 수강생의 출석 여부 변경
    void changeAttendance(AttendanceRequest.AttendanceInfo attendanceInfo);
}
