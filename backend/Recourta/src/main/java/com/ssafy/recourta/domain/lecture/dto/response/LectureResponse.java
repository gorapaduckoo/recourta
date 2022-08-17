package com.ssafy.recourta.domain.lecture.dto.response;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import lombok.*;
import org.json.simple.JSONArray;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

import static com.ssafy.recourta.global.util.LectureUtil.stringToJsonArray;

@Data
@Getter
@NoArgsConstructor
public class LectureResponse {

    @Data
    public static class LectureId {
    // 강의 생성, 삭제, 업데이트 시 변경된 강의의 강의번호를 전달해주는 Response Dto

        @NotNull
        private Integer lectureId;

        @Builder
        public LectureId(Integer lectureId) {
            this.lectureId = lectureId;
        }
    }

    @Data
    public static class LectureDetail {
        // 강의정보 세부조회용 Response Dto
        @NotNull
        private Integer lectureId;
        @NotNull
        private Integer userId; // 강의 개설자 회원번호
        // 무한참조를 막기 위해 Lecture의 멤버 객체 User에게서 PK만 가져옴
        // User 객체를 넣으면 여기서 User 참조 -> User에서는 List<Lecture> 참조 -> List<Lecture>에서 다시 User 참조 -> ... 무한반복!

        @NotNull
        private String teacher;
        @NotNull
        private String title; // 강의명
        @NotNull
        private String content; // 강의설명
        @NotNull
        private LocalDate startDate; // 시작일자
        @NotNull
        private LocalDate endDate; // 종료일자
        private String lectureImg; // 썸네일 이미지
        @NotBlank
        private JSONArray lectureTime; // 강의시간

        private Integer sessionId;

        @Builder
        public LectureDetail(Integer lectureId, Integer userId, String teacher, String title, String content, LocalDate startDate, LocalDate endDate, String lectureImg, JSONArray lectureTime) {
            this.lectureId = lectureId;
            this.userId = userId;
            this.teacher = teacher;
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

        @NotNull
        private Integer lectureId;
        @NotNull
        private String title;
        @NotNull
        private String teacher;

        private String lectureImg;
        @NotBlank
        private JSONArray lectureTime;

        // 현재 참여가능한 수업 중 가장 앞에 있는(= 끝나지 않은 수업 중 가장 빠른) 수업
        private Integer sessionId;


        @Builder
        public LecturePreview(Integer lectureId, String title, String teacher, String lectureImg, JSONArray lectureTime) {
            this.lectureId = lectureId;
            this.title = title;
            this.teacher = teacher;
            this.lectureImg = lectureImg;
            this.lectureTime = lectureTime;
        }
    }


}
