package com.ssafy.recourta.domain.session.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "강의 개설/조회/수정/삭제 시 입력받는 양식")
@Getter
public class SessionRequest {

    static String[] weekDays = {"", "월", "화", "수", "목", "금", "토", "일"};

    @Data
    @NoArgsConstructor
    public static class SessionCreateForm {

        private Integer weekDay;
        private Integer startHour;
        private Integer startMinute;
        private Integer endHour;
        private Integer endMinute;

        @Builder
        public SessionCreateForm(Integer weekDay, Integer startHour, Integer startMinute, Integer endHour, Integer endMinute) {
            this.weekDay = weekDay;
            this.startHour = startHour;
            this.startMinute = startMinute;
            this.endHour = endHour;
            this.endMinute = endMinute;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(weekDays[this.weekDay]+ " ");
            sb.append(this.startHour+":"+this.startMinute + " ~ ");
            sb.append(this.endHour+":"+this.endMinute);
            return sb.toString();
        }
    }


}
