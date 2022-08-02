package com.ssafy.recourta.domain.lecture.entity;


import com.ssafy.recourta.domain.user.entity.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "lecture")
public class Lecture {
// 강의정보 테이블

    @Id
//    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lectureId; // 강의번호

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user; // 개설자
    private String title; // 강의명
    private String content; // 강의설명
    private LocalDate startDate; // 시작일자
    private LocalDate endDate; // 종료일자
    private String lectureImg; // 썸네일 이미지

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

    public void update(String content, LocalDate startDate, LocalDate endDate, String lectureImg, String lectureTime) {
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureImg = lectureImg;
        this.lectureTime = lectureTime;
    }


}