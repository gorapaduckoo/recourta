package com.ssafy.recourta.domain.notice.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
public class NoticeRequest {

    @Data
    @NoArgsConstructor
    public static class NoticeCreateForm {
        @NotNull
        private List<Integer> userIds; // 수신자 명단
        @NotNull
        private Integer lectureId;
        @NotNull
        private String title;
        @NotNull
        private String content;

        @Builder
        public NoticeCreateForm(List<Integer> userIds, Integer lectureId, String title, String content) {
            this.userIds = userIds;
            this.lectureId = lectureId;
            this.title = title;
            this.content = content;
        }
    }
}
