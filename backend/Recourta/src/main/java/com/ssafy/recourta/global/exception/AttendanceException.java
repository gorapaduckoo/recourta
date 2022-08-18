package com.ssafy.recourta.global.exception;

public class AttendanceException {

    public static class NoSuchAttendanceException extends RuntimeException {
        public NoSuchAttendanceException() { super("해당하는 출석 정보가 없습니다."); }
    }

    public static class InvalidTypeOfAttendanceException extends RuntimeException {
        public InvalidTypeOfAttendanceException() { super("잘못된 출석 정보입니다."); }
    }
}
