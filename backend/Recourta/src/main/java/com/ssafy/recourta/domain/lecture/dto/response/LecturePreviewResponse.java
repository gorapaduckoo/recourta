package com.ssafy.recourta.domain.lecture.dto.response;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import lombok.*;
import org.json.simple.JSONArray;

import static com.ssafy.recourta.global.util.LectureUtil.stringToJsonArray;


@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class LecturePreviewResponse {

    private Integer lectureId;
    private String title;
    private String teacher;
    private String lectureImg;
    private JSONArray lectureTime;

    private Integer sessionId;

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    @Builder
    public LecturePreviewResponse(Integer lectureId, String title, String teacher, String lectureImg, JSONArray lectureTime) {
        this.lectureId = lectureId;
        this.title = title;
        this.teacher = teacher;
        this.lectureImg = lectureImg;
        this.lectureTime = lectureTime;
    }

    public static LecturePreviewResponse from(Lecture lecture) {
        return LecturePreviewResponse.builder()
                .lectureId(lecture.getLectureId())
                .title(lecture.getTitle())
                .teacher(lecture.getUser().getName())
                .lectureImg(lecture.getLectureImg())
                .lectureTime(stringToJsonArray(lecture.getLectureTime()))
                .build();
    }


}
