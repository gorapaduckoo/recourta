package com.ssafy.recourta.domain.lecture.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class LectureReadResponse {
    // 강의정보 세부조회용 Response Dto
    private Integer lectureId;
    private Integer userId; // 강의 개설자 회원번호
    // 무한참조를 막기 위해 Lecture의 멤버 객체 User에게서 PK만 가져옴
    // User 객체를 넣으면 여기서 User 참조 -> User에서는 List<Lecture> 참조 -> List<Lecture>에서 다시 User 참조 -> ... 무한반복!
    private String title; // 강의명
    private String desc; // 강의설명
    private Date startDate; // 시작일자
    private Date endDate; // 종료일자
    private String lectureImg; // 썸네일 이미지

    @Builder
    public LectureReadResponse(Integer lectureId, Integer userId, String title, String desc, Date startDate, Date endDate, String lectureImg) {
        this.lectureId = lectureId;
        this.userId = userId;
        this.title = title;
        this.desc = desc;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureImg = lectureImg;
    }

}
