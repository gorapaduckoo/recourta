package com.ssafy.recourta.domain.registration.dto.response;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.user.entity.User;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class RegistrationResponse {

    @Getter
    public static class LectureList {
        private List<Lecture> lectureList;

        @Builder
        public LectureList(List<Lecture> lectureList) {
            this.lectureList = lectureList;
        }
    }

    @Getter
    public static class UserList {
        private List<User> userList;

        @Builder
        public UserList(List<User> userList) {
            this.userList = userList;
        }
    }
}
