package com.ssafy.recourta.domain.registration.dto.response;

import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;
import com.ssafy.recourta.domain.user.dto.response.UserResponse;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class RegistrationResponse {

    @Getter
    public static class LectureList {
        private List<LectureResponse.LecturePreview> lectureList;

        @Builder
        public LectureList(List<LectureResponse.LecturePreview> lectureList) {
            this.lectureList = lectureList;
        }
    }

    @Getter
    public static class UserList {
        private List<UserResponse.Search> userList;

        @Builder
        public UserList(List<UserResponse.Search> userList) {
            this.userList = userList;
        }
    }

    @Getter
    public static class CurrentLectureList {
        private List<LectureResponse.LecturePreview> lectureList;

        @Builder CurrentLectureList(List<LectureResponse.LecturePreview> lectureList) {
            this.lectureList = lectureList;
        }
    }

    @Getter
    public static class PreviousLectureList {
        private List<LectureResponse.LecturePreview> lectureList;

        @Builder PreviousLectureList(List<LectureResponse.LecturePreview> lectureList) {
            this.lectureList = lectureList;
        }
    }

    @Getter
    public static class RegistrationId {
        private long registrationId;

        @Builder RegistrationId(long registrationId) {
            this.registrationId = registrationId;
        }
    }
}
