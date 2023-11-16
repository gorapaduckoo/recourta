package com.ssafy.recourta.domain.lecture.entity;


import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;
import com.ssafy.recourta.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

import static com.ssafy.recourta.global.util.LectureUtil.stringToJsonArray;

@Entity
@NoArgsConstructor
@Getter
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lectureId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String lectureImg;

    private String lectureTime;

    @Builder
    public Lecture(User user, String title, String description, LocalDate startDate, LocalDate endDate, String lectureImg, String lectureTime) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureImg = lectureImg;
        this.lectureTime = lectureTime;
    }

    public void update(String description, LocalDate startDate, LocalDate endDate, String lectureTime, User user) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureTime = lectureTime;
        this.user = user;
    }

    public void update(String lectureImg) {
        this.lectureImg = lectureImg;
    }

    public LectureResponse.LectureDetail toLectureDetail() {
        return LectureResponse.LectureDetail.builder()
                .lectureId(this.getLectureId())
                .userId(this.getUser().getUserId())
                .teacher(this.getUser().getName())
                .title(this.getTitle())
                .description(this.getDescription())
                .startDate(this.getStartDate())
                .endDate(this.getEndDate())
                .lectureImg(this.getLectureImg())
                .lectureTime(stringToJsonArray(this.getLectureTime()))
                .build();
    }

    public LectureResponse.LecturePreview toLecturePreview() {
        return LectureResponse.LecturePreview.builder()
                .lectureId(this.getLectureId())
                .teacher(this.getUser().getName())
                .title(this.getTitle())
                .lectureImg(this.getLectureImg())
                .lectureTime(stringToJsonArray(this.getLectureTime()))
                .build();
    }

}