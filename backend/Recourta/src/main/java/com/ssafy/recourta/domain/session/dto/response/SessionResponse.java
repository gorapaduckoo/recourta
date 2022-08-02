package com.ssafy.recourta.domain.session.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class SessionResponse {

    @Data
    public static class SessionId {
        private Integer sessionId;

        @Builder
        public SessionId(Integer sessionId){
            this.sessionId = sessionId;
        }
    }

    public interface SessionIdMapping {
        Integer getSessionId();
    }
}
