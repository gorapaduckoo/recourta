package com.ssafy.recourta.domain.lecture.dto.request;

import com.ssafy.recourta.domain.session.dto.request.SessionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;

@Schema(description = "강의 수정 시 입력받는 양식")
@Getter
@NoArgsConstructor
public class LectureUpdateRequest {
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String description;
    @NotNull(message = "개강일을 입력해주세요.")
    private LocalDate startDate;
    @NotNull(message = "종강일을 입력해주세요.")
    private LocalDate endDate;
    @NotNull(message = "강의 시간을 입력해주세요.")
    private ArrayList<SessionRequest.SessionCreateForm> lectureTime;

    @Builder
    public LectureUpdateRequest(String description, LocalDate startDate, LocalDate endDate, ArrayList<SessionRequest.SessionCreateForm> lectureTime){
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureTime = lectureTime;
    }

    // TODO toEntity를 만들것인가?
}
