package com.ssafy.recourta.domain.registration.dto.request;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.registration.entity.Registration;
import com.ssafy.recourta.domain.user.entity.User;
import lombok.*;

public class RegistrationRequest {

    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class RegistrationInfo {
        private Integer userId;
        private Integer lectureId;

        @Builder
        public RegistrationInfo(Integer userId, Integer lectureId) {
            this.userId = userId;
            this.lectureId = lectureId;
        }

        public Registration toEntity(User user, Lecture lecture) {
            return Registration.builder()
                                .user(user)
                                .lecture(lecture)
                                .build();
        }
    }
}
