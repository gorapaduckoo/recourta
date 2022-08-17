package com.ssafy.recourta.domain.attendance.service;

import com.ssafy.recourta.domain.attendance.dto.response.AttendanceResponse;
import com.ssafy.recourta.domain.attendance.entity.Attendance;
import com.ssafy.recourta.domain.attendance.repository.AttendanceRepository;
import com.ssafy.recourta.domain.checkinout.entity.CheckInOut;
import com.ssafy.recourta.domain.checkinout.respository.CheckInOutRepository;
import com.ssafy.recourta.domain.registration.entity.Registration;
import com.ssafy.recourta.domain.registration.repository.RegistrationRepository;
import com.ssafy.recourta.domain.session.entity.Session;
import com.ssafy.recourta.domain.session.repository.SessionRepository;
import com.ssafy.recourta.global.exception.SessionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    CheckInOutRepository checkInOutRepository;
    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public void recordAllAttendanceOfSession(Integer sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(SessionNotFoundException::new);
        Integer teacherId = session.getLecture().getUser().getUserId();
        List<CheckInOut> teacherCheckInOutList = checkInOutRepository.findAllByUserUserIdAndSessionSessionId(teacherId, sessionId);

        // 강의자의 총 접속 시간 계산
        long totalLengthOfSessionInSeconds = getTotalLengthOfSession(teacherCheckInOutList);
        // 강의자의 최소 접속 시간
        CheckInOut checkInOut = checkInOutRepository.findFirstByUserUserIdAndSessionSessionIdOrderByEnterTimeAsc(teacherId, sessionId);
        LocalDateTime startOfSession = checkInOut.getEnterTime();

        // 강의 수강생 목록 조회
        List<Registration> registrationList = registrationRepository.findAllByLectureLectureId(session.getLecture().getLectureId());

        for(Registration registration : registrationList) {
            Integer userId = registration.getUser().getUserId();
            if(userId.equals(teacherId)) continue;   // 강의자는 산정에서 제외한다.
            List<CheckInOut> studentCheckInOutList = checkInOutRepository.findAllByUserUserIdAndSessionSessionId(userId, sessionId);

            // 수강생의 총 접속 시간 계산
            long totalLengthOfStudentSessionInSeconds = getTotalLengthOfSession(studentCheckInOutList);
            // 수강생의 입실 시간
            CheckInOut studentCheckInOut = checkInOutRepository.findFirstByUserUserIdAndSessionSessionIdOrderByEnterTimeAsc(userId, sessionId);
            LocalDateTime studentEnterTime = studentCheckInOut.getEnterTime();

            int attType = 1;
            if(totalLengthOfStudentSessionInSeconds < (long)(0.5 * totalLengthOfSessionInSeconds)) attType = 3;
            else if(ChronoUnit.MILLIS.between(startOfSession, studentEnterTime) >= 600000) attType = 2;

            Attendance attendance = Attendance.builder()
                                              .user(registration.getUser())
                                              .session(session)
                                              .attType(attType)
                                              .build();

            attendanceRepository.save(attendance);
        }

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

    public long getTotalLengthOfSession(List<CheckInOut> checkInOutList) {
        long totalLengthOfSessionInSeconds = 0L;

        for(CheckInOut checkInOut : checkInOutList) {
            LocalDateTime enter = checkInOut.getEnterTime();
            LocalDateTime exit = checkInOut.getExitTime();
            if(exit == null) exit = LocalDateTime.now();
            long sessionLength = ChronoUnit.SECONDS.between(enter, exit);
            totalLengthOfSessionInSeconds += sessionLength;
        }

        return totalLengthOfSessionInSeconds;
    }
}