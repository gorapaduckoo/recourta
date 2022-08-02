package com.ssafy.recourta.domain.lecture.dto.request;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.session.dto.request.SessionRequest;
import com.ssafy.recourta.domain.session.entity.Session;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Data
@Schema(description = "강의 개설/조회/수정/삭제 시 입력받는 양식")
@Getter
public class LectureRequest {

    @Data
    @NoArgsConstructor
    public static class LectureCreateForm {
        // 강의 개설 시 입력받는 폼 양식

        private Integer userId; // 강의 개설자의 회원번호
        private String title;
        private String content;
        private LocalDate startDate;
        private LocalDate endDate;
        private String lectureImg;
        private ArrayList<SessionRequest.SessionCreateForm> lectureTime;

        @Builder
        public LectureCreateForm(Integer userId, String title, String content, LocalDate startDate, LocalDate endDate, String lectureImg, ArrayList<SessionRequest.SessionCreateForm> lectureTime){
            this.userId = userId;
            this.title = title;
            this.content = content;
            this.startDate = startDate;
            this.endDate = endDate;
            this.lectureImg = lectureImg;
            this.lectureTime = lectureTime;
        }

        public Lecture toEntity(){
            return Lecture.builder()
                    .title(title)
                    .content(content)
                    .startDate(startDate)
                    .endDate(endDate)
                    .lectureImg(lectureImg)
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class LectureUpdateForm {
        // 강의 수정 시 입력받는 폼 양식
        private String content;
        private LocalDate startDate;
        private LocalDate endDate;
        private String lectureImg;
        private ArrayList<SessionRequest.SessionCreateForm> lectureTime;

        @Builder
        public LectureUpdateForm(String content, LocalDate startDate, LocalDate endDate, String lectureImg, ArrayList<SessionRequest.SessionCreateForm> lectureTime){
            this.content = content;
            this.startDate = startDate;
            this.endDate = endDate;
            this.lectureImg = lectureImg;
            this.lectureTime = lectureTime;
        }

    }

    // Read, Delete는 URL에 lectureID가 포함되어있음 => 따로 requestDto 만들 필요 X

}
