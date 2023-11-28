package com.ssafy.recourta.domain.session.entity;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sessionId;

    @ManyToOne
    @JoinColumn(name = "lectureId")
    private Lecture lecture;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    public Session(Lecture lecture, LocalDateTime startTime, LocalDateTime endTime) {
        this.lecture = lecture;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
