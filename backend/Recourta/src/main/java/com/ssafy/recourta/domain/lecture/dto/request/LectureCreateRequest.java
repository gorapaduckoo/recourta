package com.ssafy.recourta.domain.lecture.dto.request;

import com.ssafy.recourta.domain.lecture.entity.Lecture;

import java.util.Date;

public class LectureCreateRequest {
    // 강의 개설 시 입력받는 폼 양식

    private Integer userId; // 강의 개설자의 회원번호
    private String title;
    private String desc;
    private Date startDate;
    private Date endDate;
    private String lectureImg;

    public Lecture toEntity(){
        return Lecture.builder()
                .title(title)
                .desc(desc)
                .build();
    }
}
