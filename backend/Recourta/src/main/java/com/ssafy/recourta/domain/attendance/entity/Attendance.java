package com.ssafy.recourta.domain.attendance.entity;

import com.ssafy.recourta.domain.attendance.dto.response.AttendanceResponse;
import com.ssafy.recourta.domain.session.entity.Session;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.global.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Attendance extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "sessionId")
    private Session session;

    // 출결상태
    // 0 : 정상 출석, 1 : 지각, 2 : 조퇴, 3 : 결석
    private Integer attType;

    @Builder
    public Attendance(User user, Session session, Integer attType) {
        this.user = user;
        this.session = session;
        this.attType = attType;
    }

    public AttendanceResponse.AttendanceInfo toAttendanceInfo() {
        return AttendanceResponse.AttendanceInfo.builder()
                                                .userId(this.getUser().getUserId())
                                                .attType(this.attType)
                                                .build();
    }
}
