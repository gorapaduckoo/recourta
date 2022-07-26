package com.ssafy.recourta.domain.lecture.service;

import com.ssafy.recourta.domain.lecture.dto.request.LectureCreateRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureReadResponse;
import com.ssafy.recourta.domain.lecture.entity.Lecture;

public interface LectureService {

    LectureReadResponse findByLectureId(Integer lectureId) throws Exception;
    Integer createLecture(LectureCreateRequest lecture) throws Exception;
}
