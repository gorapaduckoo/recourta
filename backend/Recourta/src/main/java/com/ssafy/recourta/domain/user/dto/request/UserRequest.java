package com.ssafy.recourta.domain.user.dto.request;

import com.ssafy.recourta.domain.user.entity.User;
import lombok.*;

public class UserRequest {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
        private String name;
        private String email;
        private String password;
        private int isStudent;
        private String userImg;

        public User create(){
            return User.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .isStudent(isStudent)
                    .userImg(userImg)
                    .build();
        }
    }



    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Update {
        private String password;
        private String userImg;

    public User update(){
        return User.builder()
                .password(password)
                .userImg(userImg)
                .build();
        }
    }
}
