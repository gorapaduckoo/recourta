package com.ssafy.recourta.domain.session.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class SessionResponse {

    @Data
    public static class SessionId {
        @NotNull
        private Integer sessionId;

        @Builder
        public SessionId(Integer sessionId){
            this.sessionId = sessionId;
        }
    }

    public interface SessionIdMapping {
        Integer getSessionId();
    }

    @Data
    public static class SessionStartTime {
        private LocalDateTime startTime;

        @Builder
        public SessionStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }
    }
}
