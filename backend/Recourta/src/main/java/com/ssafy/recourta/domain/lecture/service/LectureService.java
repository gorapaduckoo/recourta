package com.ssafy.recourta.domain.lecture.service;

import com.ssafy.recourta.domain.lecture.dto.request.LectureCreateRequest;
import com.ssafy.recourta.domain.lecture.dto.request.LectureUpdateRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureDetailResponse;
import com.ssafy.recourta.domain.lecture.dto.response.LectureIdResponse;
import com.ssafy.recourta.domain.lecture.dto.response.LecturePreviewResponse;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

public interface LectureService {

    LectureIdResponse createLecture(LectureCreateRequest lecture, MultipartFile lectureImg) throws Exception;
    LectureDetailResponse searchByLectureId(Integer lectureId) throws Exception;
    LectureIdResponse updateLecture(Integer lectureId, LectureUpdateRequest lecture, MultipartFile lectureImg) throws Exception;
    LectureIdResponse deleteLecture(Integer lectureId) throws Exception;
    List<LecturePreviewResponse> searchMyCurrentTeachingLecture(Integer userId) throws Exception;
    List<LecturePreviewResponse> searchMyLecture(Integer userId) throws ParseException;
    List<LectureDetailResponse> searchAvailableLecture(Integer userId) throws Exception;

}
