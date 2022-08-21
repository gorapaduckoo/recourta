package com.ssafy.recourta.domain.checkinout.entity;

import com.ssafy.recourta.domain.session.entity.Session;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.global.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class CheckInOut extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "sessionId")
    private Session session;

    private LocalDateTime enterTime;
    private LocalDateTime exitTime;

    @Builder
    public CheckInOut(User user, Session session, LocalDateTime enterTime) {
        this.user = user;
        this.session = session;
        this.enterTime = enterTime;
    }
}
