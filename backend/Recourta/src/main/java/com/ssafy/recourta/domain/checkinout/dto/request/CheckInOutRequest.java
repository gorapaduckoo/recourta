package com.ssafy.recourta.domain.checkinout.dto.request;

import com.ssafy.recourta.domain.checkinout.entity.CheckInOut;
import com.ssafy.recourta.domain.session.entity.Session;
import com.ssafy.recourta.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.net.Inet4Address;
import java.time.LocalDateTime;

public class CheckInOutRequest {

    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class CheckInInfo {
        private Integer userId;
        private Integer sessionId;

        @Builder
        public CheckInInfo(Integer userId, Integer sessionId) {
            this.userId = userId;
            this.sessionId = sessionId;
        }

        public CheckInOut toEntity(User user, Session session) {
            return CheckInOut.builder()
                    .user(user)
                    .session(session)
                    .enterTime(LocalDateTime.now())
                    .build();
        }
    }

    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class CheckOutInfo {
        private Integer userId;
        private Integer sessionId;
        private Long checkId;

        @Builder
        public CheckOutInfo(Integer userId, Integer sessionId, Long checkId) {
            this.userId = userId;
            this.sessionId = sessionId;
            this.checkId = checkId;
        }
    }
}
