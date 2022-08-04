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
//        private String userImg;

        public User create(){
            return User.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .isStudent(isStudent)
//                    .userImg(userImg)
                    .build();
        }
    }



//    @Builder
//    @Getter
//    @NoArgsConstructor(access = AccessLevel.PRIVATE)
//    @AllArgsConstructor(access = AccessLevel.PRIVATE)
//    public static class Update {
//        private String password;
//        private String userImg;
//
//    public User update(){
//        return User.builder()
//                .password(password)
//                .userImg(userImg)
//                .build();
//        }
//    }


    // 빌드 빼기!!!
    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UpdateImg {

        private int userId;

//        public User update(){
//            return User.builder()
//                    .userImg(userImg)
//                    .build();
//        }
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UpdatePw {

        private int userId;
        private String nowPw;
        private String newPw;

    }

//    @Getter
//    @NoArgsConstructor(access = AccessLevel.PRIVATE)
//    @AllArgsConstructor(access = AccessLevel.PRIVATE)
//    public static class CheckMail {
//
//        private String email;
//
//    }

    //로그인
    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Dologin {

        private String email;
        private String password;

    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CodeCheck {

        private String email;
        private String code;

    }

}
