package com.ssafy.recourta.domain.user.dto.request;

import com.ssafy.recourta.domain.user.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRequest {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        private String name;
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        @Email
        private String email;
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*\\W)(?=\\S+$).{8,20}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;
        @NotNull
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




    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UpdateImg {

        private int userId;

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

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ResetPw {

        private String code;
        private String newPw;

    }

}
