package com.ssafy.recourta.domain.user.dto.response;

import com.ssafy.recourta.domain.user.entity.User;
import lombok.*;

public class UserResponse {


    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OnlyId {
        private int userId;

        public static UserResponse.OnlyId build(User user){
            return UserResponse.OnlyId.builder()
                    .userId(user.getUserId())
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Search {
        private int userId;
        private String name;
        private String email;
        private String userImg;

        public static UserResponse.Search build(User user){
            return Search.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .userImg(user.getUserImg())
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class isSuccess {
        private boolean success;

        public static UserResponse.isSuccess build(boolean success){
            return isSuccess.builder()
                    .success(success)
                    .build();
        }
    }
}
