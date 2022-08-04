package com.ssafy.recourta.domain.notice.entity;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.user.entity.User;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="notice")
public class Notice {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "lectureId")
    private Lecture lecture; // 송신 강의

    @ManyToOne
    @NotNull
    @JoinColumn(name = "userId")
    private User user; // 수신자

    @NotNull
    String title; // 제목
    @NotNull
    String content; // 내용
    @NotNull
    LocalDateTime datetime; // 송신일시

    Boolean isChecked; // 1: 읽음, 0: 읽지않음

    @Builder
    public Notice(Lecture lecture, User user, String title, String content, LocalDateTime datetime, boolean isChecked) {
        this.lecture = lecture;
        this.user = user;
        this.title = title;
        this.content = content;
        this.datetime = datetime;
        this.isChecked = isChecked;
    }
}
