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
    public static class LecturePreviewList {
        private List<LectureResponse.LecturePreview> lecturePreviewList;

        @Builder
        public LecturePreviewList(List<LectureResponse.LecturePreview> lecturePreviewList) {
            this.lecturePreviewList = lecturePreviewList;
        }
    }

    @Getter
    public static class LectureDetailList {
        private List<LectureResponse.LectureDetail> lectureDetailList;

        @Builder
        public LectureDetailList(List<LectureResponse.LectureDetail> lectureDetailList) {
            this.lectureDetailList = lectureDetailList;
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
        private List<LectureResponse.LecturePreview> lecturePreviewList;

        @Builder CurrentLectureList(List<LectureResponse.LecturePreview> lecturePreviewList) {
            this.lecturePreviewList = lecturePreviewList;
        }
    }

    @Getter
    public static class PreviousLectureList {
        private List<LectureResponse.LecturePreview> lecturePreviewList;

        @Builder PreviousLectureList(List<LectureResponse.LecturePreview> lecturePreviewList) {
            this.lecturePreviewList = lecturePreviewList;
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
