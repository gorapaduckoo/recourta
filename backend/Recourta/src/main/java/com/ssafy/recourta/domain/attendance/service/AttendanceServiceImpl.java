package com.ssafy.recourta.domain.attendance.service;

import com.ssafy.recourta.domain.attendance.dto.response.AttendanceResponse;

public class AttendanceServiceImpl implements AttendanceService {
    @Override
    public void recordAllAttendanceOfSession(Integer sessionId) {

    }

    @Override
    public AttendanceResponse.SessionAttendance getAttendanceOfSession(Integer sessionId) {
        return null;
    }

    @Override
    public AttendanceResponse.LectureAttendance getAttendanceOfLecture(Integer lectureId) {
        return null;
    }

    @Override
    public void changeAttendance(Integer userId, Integer sessionId) {

    }
}
