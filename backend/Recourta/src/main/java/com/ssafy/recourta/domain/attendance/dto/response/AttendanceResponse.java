package com.ssafy.recourta.domain.attendance.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class AttendanceResponse {

    // 개별적인 출석 정보
    @Getter
    public static class AttendanceInfo {
        private Integer userId;
        private Integer attType;

        @Builder
        public AttendanceInfo(Integer userId, Integer attType) {
            this.userId = userId;
            this.attType = attType;
        }
    }

    // 수업시간의 모든 출석 정보
    @Getter
    public static class SessionAttendance {
        private Integer sessionId;
        private List<AttendanceInfo> sessionAttendance;

        @Builder
        public SessionAttendance(Integer sessionId, List<AttendanceInfo> sessionAttendance) {
            this.sessionId = sessionId;
            this.sessionAttendance = sessionAttendance;
        }
    }

    // 강의의 모든 출석정보
    @Getter
    public static class LectureAttendance {
        private Integer lectureId;
        private List<SessionAttendance> lectureAttendance;

        @Builder
        public LectureAttendance(Integer lectureId, List<SessionAttendance> lectureAttendance) {
            this.lectureId = lectureId;
            this.lectureAttendance = lectureAttendance;
        }
    }
}
