package com.ssafy.recourta.domain.lecture.dto.request;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.sql.Date;

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
        private Date startDate;
        private Date endDate;
        private String lectureImg;
        private ArrayList<String> lectureTime;

        @Builder
        public LectureCreateForm(Integer userId, String title, String content, Date startDate, Date endDate, String lectureImg, ArrayList<String> lectureTime){
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
        private Date startDate;
        private Date endDate;
        private String lectureImg;
        private ArrayList<String> lectureTime;

        @Builder
        public LectureUpdateForm(String content, Date startDate, Date endDate, String lectureImg, ArrayList<String> lectureTime){
            this.content = content;
            this.startDate = startDate;
            this.endDate = endDate;
            this.lectureImg = lectureImg;
            this.lectureTime = lectureTime;
            System.out.println(lectureTime.toString());
        }

    }

    // Read, Delete는 URL에 lectureID가 포함되어있음 => 따로 requestDto 만들 필요 X

}
