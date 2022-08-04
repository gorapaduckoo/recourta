package com.ssafy.recourta.domain.notice.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class NoticeResponse {

    @Data
    public static class NoticeTitle {
        private Integer noticeId;
        private String lectureTitle;
        private String title;
        private LocalDate date;
        private boolean isChecked;

        @Builder
        public NoticeTitle(Integer noticeId, String lectureTitle, String title, LocalDate date, boolean isChecked) {
            this.noticeId = noticeId;
            this.lectureTitle = lectureTitle;
            this.title = title;
            this.date = date;
            this.isChecked = isChecked;

        }
    }



    @Data
    public static class NoticeDetail {
        private Integer noticeId;
        private String lectureTitle;
        private String title;
        private String content;
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
        private Integer noticeCnt;

        @Builder
        public NoticeCount(Integer noticeCnt){
            this.noticeCnt = noticeCnt;
        }
    }
}
