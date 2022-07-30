package com.ssafy.recourta.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

public class LectureException{

    public static class LectureSaveFail extends RuntimeException{
        public LectureSaveFail(){
            super("새로운 강의 저장에 실패했습니다.");
        }
    }

    public static class LectureUpdateFail extends RuntimeException {
        public LectureUpdateFail() {
            super("강의 정보 업데이트에 실패했습니다.");
        }
    }

    public static class NullLecture extends RuntimeException {
        public NullLecture(){
            super("강의정보를 불러오지 못했습니다.");
        }
    }

    public static class UnvalidLectureId extends RuntimeException {
        public UnvalidLectureId(String lectureId){
            super(lectureId +"는 존재하지 않는 강의번호입니다.");
        }
    }


}
