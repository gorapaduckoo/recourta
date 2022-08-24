package com.ssafy.recourta.domain.checkinout.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CheckInOutResponse {

    @Getter
    public static class CheckId {
        private long checkId;

        @Builder
        public CheckId(long checkId) {
            this.checkId = checkId;
        }
    }
}
