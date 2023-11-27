package com.ssafy.recourta.domain.lecture.dto.request;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.session.dto.request.SessionRequest;
import com.ssafy.recourta.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;

@Schema(description = "강의 개설 시 입력받는 양식")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureCreateRequest {
    @NotNull(message = "강의 생성자의 아이디값은 필수입니다.")
    private Integer userId;

    @NotBlank(message = "강의명을 입력해주세요.")
    @Max(value = 50, message = "강의명은 최대 50자입니다.")
    private String title;

    @NotBlank(message = "강의 설명을 입력해주세요.")
    private String description;

    @NotNull(message = "개강일을 입력해주세요.")
    private LocalDate startDate;

    @NotNull(message = "종강일을 입력해주세요.")
    private LocalDate endDate;

    @NotNull(message = "강의 시간을 입력해주세요.")
    private ArrayList<SessionRequest.SessionCreateForm> lectureTime;

    @Builder
    public LectureCreateRequest(Integer userId, String title, String description, LocalDate startDate, LocalDate endDate, ArrayList<SessionRequest.SessionCreateForm> lectureTime){
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureTime = lectureTime;
    }

    public Lecture toEntity(User user){
        return Lecture.builder()
                .user(user)
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .lectureTime(lectureTime.toString())
                .build();
    }
}
