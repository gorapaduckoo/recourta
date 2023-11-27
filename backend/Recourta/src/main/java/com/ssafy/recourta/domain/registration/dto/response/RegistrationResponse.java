package com.ssafy.recourta.domain.registration.dto.response;

import com.ssafy.recourta.domain.lecture.dto.response.LectureDetailResponse;
import com.ssafy.recourta.domain.lecture.dto.response.LecturePreviewResponse;
import com.ssafy.recourta.domain.user.dto.response.UserResponse;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class RegistrationResponse {

    @Getter
    public static class LecturePreviewList {
        private List<LecturePreviewResponse> lecturePreviewList;

        @Builder
        public LecturePreviewList(List<LecturePreviewResponse> lecturePreviewList) {
            this.lecturePreviewList = lecturePreviewList;
        }
    }

    @Getter
    public static class LectureDetailList {
        private List<LectureDetailResponse> lectureDetailList;

        @Builder
        public LectureDetailList(List<LectureDetailResponse> lectureDetailList) {
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
        private List<LecturePreviewResponse> lecturePreviewList;

        @Builder CurrentLectureList(List<LecturePreviewResponse> lecturePreviewList) {
            this.lecturePreviewList = lecturePreviewList;
        }
    }

    @Getter
    public static class PreviousLectureList {
        private List<LecturePreviewResponse> lecturePreviewList;

        @Builder PreviousLectureList(List<LecturePreviewResponse> lecturePreviewList) {
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
