package com.ssafy.recourta.domain.lecture.dto.response;

import lombok.*;
import org.json.simple.JSONArray;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@NoArgsConstructor
public class LectureResponse {

    @Data
    public static class LectureId {
    // 강의 생성, 삭제, 업데이트 시 변경된 강의의 강의번호를 전달해주는 Response Dto

        private Integer lectureId;

        @Builder
        public LectureId(Integer lectureId) {
            this.lectureId = lectureId;
        }
    }

    @Data
    public static class LectureDetail {
        // 강의정보 세부조회용 Response Dto
        private Integer lectureId;
        private Integer userId; // 강의 개설자 회원번호
        // 무한참조를 막기 위해 Lecture의 멤버 객체 User에게서 PK만 가져옴
        // User 객체를 넣으면 여기서 User 참조 -> User에서는 List<Lecture> 참조 -> List<Lecture>에서 다시 User 참조 -> ... 무한반복!
        private String title; // 강의명
        private String content; // 강의설명
        private LocalDate startDate; // 시작일자
        private LocalDate endDate; // 종료일자
        private String lectureImg; // 썸네일 이미지

        private JSONArray lectureTime; // 강의시간

        @Builder
        public LectureDetail(Integer lectureId, Integer userId, String title, String content, LocalDate startDate, LocalDate endDate, String lectureImg, JSONArray lectureTime) {
            this.lectureId = lectureId;
            this.userId = userId;
            this.title = title;
            this.content = content;
            this.startDate = startDate;
            this.endDate = endDate;
            this.lectureImg = lectureImg;
            this.lectureTime = lectureTime;
        }
    }

    // 내가 개설한 강의 중 현재 진행중인 강의 목록 반환
    @Data
    public static class LecturePreview {

        private Integer lectureId;
        private String title;
        private JSONArray lectureTime;

        @Builder
        public LecturePreview(Integer lectureId, String title, JSONArray lectureTime) {
            this.lectureId = lectureId;
            this.title = title;
            this.lectureTime = lectureTime;
        }
    }


}
