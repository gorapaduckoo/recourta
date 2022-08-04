package com.ssafy.recourta.domain.registration.entity;

import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Registration extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lectureId")
    private Lecture lecture;

    @Builder
    public Registration(User user, Lecture lecture) {
        this.user = user;
        this.lecture = lecture;
    }
}
