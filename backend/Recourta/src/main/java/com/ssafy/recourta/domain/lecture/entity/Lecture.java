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
@Table(name = "lecture")
public class Lecture {
// 강의정보 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lectureId; // 강의번호

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user; // 개설자
    @NotNull
    private String title; // 강의명
    @NotNull
    private String content; // 강의설명
    @NotNull
    private LocalDate startDate; // 시작일자
    @NotNull
    private LocalDate endDate; // 종료일자

    private String lectureImg; // 썸네일 이미지
    @NotNull
    private String lectureTime; // 강의 시간 정보

    @Builder
    public Lecture(User user, String title, String content, LocalDate startDate, LocalDate endDate, String lectureImg, String lectureTime) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureImg = lectureImg;
        this.lectureTime = lectureTime;
    }

    public void update(String content, LocalDate startDate, LocalDate endDate, String lectureTime) {
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureTime = lectureTime;
    }

    public void update(String content, LocalDate startDate, LocalDate endDate, String lectureTime, User user) {
        this.content = content;
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
                .content(this.getContent())
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