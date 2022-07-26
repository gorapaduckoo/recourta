package com.ssafy.recourta.domain.lecture.service;

import com.ssafy.recourta.domain.lecture.dto.request.LectureCreateRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureReadResponse;
import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    LectureRepository lectureRepository;


    @Override
    public LectureReadResponse findByLectureId(Integer lectureId) throws Exception {
        Lecture result = lectureRepository.findByLectureId(lectureId);
        if (result != null) {
            return LectureReadResponse.builder()
                    .lectureId(result.getLectureId())
                    .userId(result.getUser().getUserId())
                    .title(result.getTitle())
                    .desc(result.getDesc())
                    .startDate(result.getStartDate())
                    .endDate(result.getEndDate())
                    .lectureImg(result.getLectureImg())
                    .build();

        }
        else {
            throw new Exception("Result of findByLectureId is null");
        }
    }

    @Override
    public Integer createLecture(LectureCreateRequest lecture) throws Exception {
        return lectureRepository.save(lecture).getLectureId();
    }
}