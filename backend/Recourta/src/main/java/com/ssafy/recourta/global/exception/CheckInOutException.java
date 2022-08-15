package com.ssafy.recourta.global.exception;

public class CheckInOutException {

    public static class InvalidCheckInOutData extends RuntimeException {
        public InvalidCheckInOutData() {
            super("존재하지 않는 입퇴실 정보입니다.");
        }
    }
}
