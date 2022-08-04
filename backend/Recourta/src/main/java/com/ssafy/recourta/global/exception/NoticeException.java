package com.ssafy.recourta.global.exception;

public class NoticeException{

    public static class NoticeSaveFail extends RuntimeException{
        public NoticeSaveFail(){
            super("알림 생성에 실패했습니다.");
        }
    }

    public static class NoticeSearchFail extends RuntimeException {
        public NoticeSearchFail(){
            super("알림 조회에 실패했습니다.");
        }
    }
}
