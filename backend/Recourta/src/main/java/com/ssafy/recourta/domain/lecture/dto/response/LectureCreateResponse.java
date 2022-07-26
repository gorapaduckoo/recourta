package com.ssafy.recourta.domain.lecture.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LectureCreateResponse {

    private Integer lectureId; // 생성한 강의의 강의번호

    @Builder
    public LectureCreateResponse(Integer lectureId) {
        this.lectureId = lectureId;
    }
}
