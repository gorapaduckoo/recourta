package com.ssafy.recourta.domain.lecture.service;

import com.ssafy.recourta.domain.lecture.dto.request.LectureRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;

import java.util.List;

public interface LectureService {

    LectureResponse.LectureId createLecture(LectureRequest.LectureCreateForm lecture) throws Exception;
    LectureResponse.LectureDetail searchByLectureId(Integer lectureId) throws Exception;
    LectureResponse.LectureId updateLecture(Integer lectureId, LectureRequest.LectureUpdateForm lecture) throws Exception;
    LectureResponse.LectureId deleteLecture(Integer lectureId) throws Exception;
    List<LectureResponse.LecturePreview> searchMyCurrentTeachingLecture(Integer userId) throws Exception;

}
