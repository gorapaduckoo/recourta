package com.ssafy.recourta.domain.session.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
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
            String sMinute, eMinute;
            if (this.startMinute < 10) {
                sMinute = "0"+this.startMinute;
            } else {
                sMinute = this.startMinute.toString();
            }
            if (this.endMinute < 10) {
                eMinute = "0"+this.endMinute;
            } else {
                eMinute = this.endMinute.toString();
            }
            sb.append(weekDays[this.weekDay]+ " ");
            sb.append(this.startHour+":"+sMinute + " ~ ");
            sb.append(this.endHour+":"+eMinute);
            return sb.toString();
        }
    }

    public static class SessionId {
        private Integer sessionId;

        @Builder
        public SessionId(Integer sessionId){
            this.sessionId = sessionId;
        }
    }


}
