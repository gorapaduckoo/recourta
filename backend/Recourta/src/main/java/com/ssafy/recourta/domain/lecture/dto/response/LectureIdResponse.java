package com.ssafy.recourta.domain.lecture.dto.response;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureIdResponse {

    @NotNull
    private Integer lectureId;

    @Builder
    public LectureIdResponse(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public static LectureIdResponse from(Lecture lecture) {
        return LectureIdResponse.builder()
                .lectureId(lecture.getLectureId())
                .build();
    }

}
