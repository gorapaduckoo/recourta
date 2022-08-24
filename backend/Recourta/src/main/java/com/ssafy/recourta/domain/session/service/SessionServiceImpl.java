package com.ssafy.recourta.domain.session.service;

import com.ssafy.recourta.domain.lecture.dto.request.LectureRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;
import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import com.ssafy.recourta.domain.session.dto.request.SessionRequest;
import com.ssafy.recourta.domain.session.dto.response.SessionResponse;
import com.ssafy.recourta.domain.session.entity.Session;
import com.ssafy.recourta.domain.session.repository.SessionRepository;
import com.ssafy.recourta.global.exception.LectureException;
import com.ssafy.recourta.global.exception.SessionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private LectureRepository lectureRepository;

    @Transactional
    @Override
    public Integer createSession(@Valid List<SessionRequest.SessionCreateForm> sessions, Integer lectureId, boolean isUpdate) {

        int result = 0;

        for(SessionRequest.SessionCreateForm s : sessions) {
            // ex) s: 월 11:30 ~ 12:00 강의
            // lectureId로 lecture 객체 조회
            // 존재하지 않는 강의번호인 경우 Exception 발생
            Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                    () -> new LectureException.UnvalidLectureId(lectureId)
            );
            LocalDate startDate;

            if (!isUpdate) { // 새로 생성된 강의인 경우 개강일부터 세션 생성
                startDate = lecture.getStartDate();
            } else { // 강의정보가 업데이트된 경우 내일부터 세션 생성
                startDate = LocalDate.now().plusDays(1);
            }
            LocalDate endDate = lecture.getEndDate();

            System.out.println(startDate + " " + endDate);
            System.out.println(s.getEndHour() + " " + s.getEndMinute());
            LocalDate targetDate = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.of(s.getWeekDay())));
            while(targetDate.isBefore(endDate.plusDays(1))){
                Session session = Session.builder()
                        .lecture(lecture)
                        .startTime(targetDate.atTime(s.getStartHour(), s.getStartMinute()))
                        .endTime(targetDate.atTime(s.getEndHour(), s.getEndMinute()))
                        .build();
                sessionRepository.save(session);
                result++;
                targetDate = targetDate.plusDays(7);
            }
        }
        return result;
    }

    // 강의 기간검색
    @Override
    public List<SessionResponse.SessionIdMapping> searchSessionByDateAndLectureId(LocalDate startDate, LocalDate endDate, Integer lectureId) {

        LocalDateTime startTime = LocalDateTime.of(startDate, LocalTime.of(0,0,0));
        LocalDateTime endTime = LocalDateTime.of(endDate, LocalTime.of(23,59,59));
        // startDate 이후 ~ endDate 이전의 강의 세션을 전부 검색
        List<SessionResponse.SessionIdMapping> sessions = sessionRepository.findAllByLecture_LectureIdAndStartTimeAfterAndEndTimeBefore(lectureId, startTime, endTime);

        return sessions;
    }

    @Override
    public Integer changeSession(@Valid List<SessionRequest.SessionCreateForm> sessions, Integer lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                () -> new LectureException.UnvalidLectureId(lectureId)
        );
        List<SessionResponse.SessionIdMapping> prevSessions = searchSessionByDateAndLectureId(LocalDate.now().plusDays(1), lecture.getEndDate(), lecture.getLectureId());
        for (SessionResponse.SessionIdMapping s : prevSessions) {
            deleteSession(s.getSessionId());
        }
        Integer sessionResult = createSession(sessions, lectureId, true);
        return sessionResult;
    }

    @Override
    public SessionResponse.SessionId deleteSession(Integer sessionId) {
        sessionRepository.deleteById(sessionId);
        return SessionResponse.SessionId.builder().sessionId(sessionId).build();
    }

    @Override
    public Integer getEarliestAvailableSession(Integer lectureId) {
        Session session = sessionRepository.findFirstByLecture_LectureIdAndEndTimeAfterOrderByEndTimeAsc(lectureId, LocalDateTime.now()).orElse(null);
        if(session != null) return session.getSessionId();
        else return null;
    }

    @Override
    public SessionResponse.SessionStartTime getSessionStartTime(Integer sessionId) {
        Session session = sessionRepository.findBySessionId(sessionId).orElseThrow(SessionNotFoundException::new);
        SessionResponse.SessionStartTime sessionStartTime = SessionResponse.SessionStartTime.builder()
                .startTime(session.getStartTime())
                .build();
        return sessionStartTime;
    }


}
