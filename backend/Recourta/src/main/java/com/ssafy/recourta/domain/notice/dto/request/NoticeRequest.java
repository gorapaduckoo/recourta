package com.ssafy.recourta.domain.notice.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
public class NoticeRequest {

    @Data
    @NoArgsConstructor
    public static class NoticeCreateForm {
        private List<Integer> userIds; // 수신자 명단
        private Integer lectureId;
        private String title;
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
