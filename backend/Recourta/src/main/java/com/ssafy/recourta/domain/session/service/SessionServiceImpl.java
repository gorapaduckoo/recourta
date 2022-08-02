package com.ssafy.recourta.domain.session.service;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import com.ssafy.recourta.domain.session.dto.request.SessionRequest;
import com.ssafy.recourta.domain.session.dto.response.SessionResponse;
import com.ssafy.recourta.domain.session.entity.Session;
import com.ssafy.recourta.domain.session.repository.SessionRepository;
import com.ssafy.recourta.global.exception.LectureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    LectureRepository lectureRepository;

    @Override
    public List<SessionResponse.SessionId> createSession(List<SessionRequest.SessionCreateForm> sessions, Integer lectureId) {

        List<SessionResponse.SessionId> result = new ArrayList<>();

        for(SessionRequest.SessionCreateForm s : sessions) {
            // ex) s: 월 11:30 ~ 12:00 강의
            // lectureId로 lecture 객체 조회
            // 존재하지 않는 강의번호인 경우 Exception 발생
            Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                    () -> new LectureException.UnvalidLectureId(lectureId.toString())
            );
            LocalDate startDate = lecture.getStartDate();
            LocalDate endDate = lecture.getEndDate();
            LocalDate targetDate = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.of(s.getWeekDay())));
            while(targetDate.isBefore(endDate.plusDays(1))){
                Session session = Session.builder()
                        .lecture(lecture)
                        .startTime(targetDate.atTime(s.getStartHour(), s.getStartMinute()))
                        .endTime(targetDate.atTime(s.getEndHour(), s.getEndMinute()))
                        .build();
                sessionRepository.save(session);
                result.add(SessionResponse.SessionId.builder()
                        .sessionId(session.getSessionId())
                        .build());
                targetDate = targetDate.plusDays(7);
            }
        }
        return result;
    }
}
