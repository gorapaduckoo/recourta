package com.ssafy.recourta.domain.attendance.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class AttendanceRequest {

    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class AttendanceInfo {
        private Integer userId;
        private Integer sessionId;
        private Integer attType;

        @Builder
        public AttendanceInfo(Integer userId, Integer sessionId, Integer attType) {
            this.userId = userId;
            this.sessionId = sessionId;
            this.attType = attType;
        }
    }
}
