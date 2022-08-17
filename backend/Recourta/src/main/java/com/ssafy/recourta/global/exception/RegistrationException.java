package com.ssafy.recourta.global.exception;

public class RegistrationException {

    public static class RegistrationFail extends RuntimeException {
        public RegistrationFail() {
            super("강의 신청에 실패하였습니다.");
        }
    }

    public static class RegistrationAlreadyExists extends RuntimeException {
        public RegistrationAlreadyExists() {
            super("이미 존재하는 수강정보입니다.");
        }
    }

    public static class RegistrationDoesNotExist extends RuntimeException {
        public RegistrationDoesNotExist() {
            super("존재하지 않는 수강정보입니다.");
        }
    }

    public static class RegistrationDeleteFail extends RuntimeException {
        public RegistrationDeleteFail() {
            super("강의 수강 철회에 실패하였습니다.");
        }
    }
}
