package com.ssafy.recourta.domain.lecture.dto.response;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import lombok.*;
import org.json.simple.JSONArray;
import java.time.LocalDate;

import static com.ssafy.recourta.global.util.LectureUtil.stringToJsonArray;


@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class LectureDetailResponse {

    private Integer lectureId;
    private Integer userId;
    private String teacher;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String lectureImg;
    private JSONArray lectureTime;

    private Integer sessionId;

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Builder
    public LectureDetailResponse (Integer lectureId, Integer userId, String teacher, String title, String description, LocalDate startDate, LocalDate endDate, String lectureImg, JSONArray lectureTime) {
        this.lectureId = lectureId;
        this.userId = userId;
        this.teacher = teacher;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureImg = lectureImg;
        this.lectureTime = lectureTime;
    }

    public static LectureDetailResponse from(Lecture lecture) {
        return LectureDetailResponse.builder()
                .lectureId(lecture.getLectureId())
                .userId(lecture.getUser().getUserId())
                .teacher(lecture.getUser().getName())
                .title(lecture.getTitle())
                .description(lecture.getDescription())
                .startDate(lecture.getStartDate())
                .endDate(lecture.getEndDate())
                .lectureImg(lecture.getLectureImg())
                .lectureTime(stringToJsonArray(lecture.getLectureTime()))
                .build();
    }
}
