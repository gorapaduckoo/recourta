package com.ssafy.recourta.domain.notice.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class NoticeResponse {

    @Data
    public static class NoticeTitle {
        @NotNull
        private Integer noticeId;
        @NotNull
        private String lectureTitle;
        @NotNull
        private String title;
        @NotNull
        private LocalDate date;
        @NotNull
        private boolean isRead;

        @Builder
        public NoticeTitle(Integer noticeId, String lectureTitle, String title, LocalDate date, boolean isRead) {
            this.noticeId = noticeId;
            this.lectureTitle = lectureTitle;
            this.title = title;
            this.date = date;
            this.isRead = isRead;

        }
    }



    @Data
    public static class NoticeDetail {
        @NotNull
        private Integer noticeId;
        @NotNull
        private String lectureTitle;
        @NotNull
        private String title;
        @NotNull
        private String content;
        @NotNull
        private LocalDateTime datetime;

        @Builder
        public NoticeDetail(Integer noticeId, String lectureTitle, String title, String content, LocalDateTime datetime) {
            this.noticeId = noticeId;
            this.lectureTitle = lectureTitle;
            this.title = title;
            this.content = content;
            this.datetime = datetime;
        }
    }

    @Data
    public static class NoticeCount {
        @NotNull
        private Integer noticeCnt;

        @Builder
        public NoticeCount(Integer noticeCnt){
            this.noticeCnt = noticeCnt;
        }
    }
}
