package com.ssafy.recourta.domain.lecture.entity;


import com.ssafy.recourta.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

}