package com.ssafy.recourta.domain.lecture.service;

import com.ssafy.recourta.domain.lecture.dto.request.LectureRequest;
import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;
import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    LectureRepository lectureRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public LectureResponse.LectureId createLecture(LectureRequest.LectureCreateForm input) throws Exception {
        // RequestDto에 담긴 userId로 user 객체 조회
        User user = userRepository.findById(input.getUserId()).orElseThrow(UserNotFoundException::new);

        System.out.println(input.getLectureTime().toString());
        // 조회한 user 객체는 저장할 lecture 객체에 담아줌
        Lecture lecture = Lecture.builder()
                .user(user)
                .title(input.getTitle())
                .content(input.getContent())
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .lectureImg(input.getLectureImg())
                .lectureTime(input.getLectureTime().toString())
                .build();

        // DB 저장 후 저장한 lecture의 lectureId를 결과값으로 반환
        Integer result = lectureRepository.save(lecture).getLectureId();
        if(result!=null){
            return LectureResponse.LectureId.builder().lectureId(result).build();
        } else {
            throw new Exception("save 실패!");
        }
    }



    @Override
    public LectureResponse.LectureDetail searchByLectureId(Integer lectureId) throws Exception {
        Lecture result = lectureRepository.findById(lectureId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 강의번호입니다. id=" + lectureId.toString()));
        if (result != null) {
            // lectureTime은 "[월 10:00 ~ 11:30, 월 18:30 ~ 19:00, ...] 같은 형식

            ////////// String To JSONArray
            // str = "월 10:00 ~ 11:30, 월 18:30 ~ 19:00, ... "
            String str = result.getLectureTime().substring(1, result.getLectureTime().length()-1);
            // arr = ["월 10:00 ~ 11:30", "월 18:30 ~ 19:00", ... ]
            String[] arr = str.split(",");
            JSONArray jsonArray = new JSONArray();
            jsonArray.addAll(Arrays.asList(arr));
            //////////
            return LectureResponse.LectureDetail.builder()
                    .lectureId(result.getLectureId())
                    .userId(result.getUser().getUserId())
                    .title(result.getTitle())
                    .content(result.getContent())
                    .startDate(result.getStartDate())
                    .endDate(result.getEndDate())
                    .lectureImg(result.getLectureImg())
                    .lectureTime(jsonArray)
                    .build();

        }
        else {
            throw new Exception("Result of findByLectureId is null");
        }
    }


    @Override
    public LectureResponse.LectureId updateLecture(Integer lectureId, LectureRequest.LectureUpdateForm lecture) throws Exception {
        Lecture updatedLecture = lectureRepository.findById(lectureId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 강의번호입니다. id=" + lectureId.toString()));
        updatedLecture.update(lecture.getContent(), lecture.getStartDate(), lecture.getEndDate(), lecture.getLectureImg(), lecture.getLectureTime().toString());
        Integer result = lectureRepository.save(updatedLecture).getLectureId();


        if (result != null) {
            return LectureResponse.LectureId.builder()
                    .lectureId(result)
                    .build();
        } else {
            throw new Exception("강의 업데이트 실패!");
        }
    }

    @Override
    public LectureResponse.LectureId deleteLecture(Integer lectureId) throws Exception {
        if(lectureRepository.existsById(lectureId)) {
            lectureRepository.deleteById(lectureId);
            return LectureResponse.LectureId.builder().lectureId(lectureId).build();
        }
        else {
            throw new Exception("존재하지 않는 강의번호입니다.");
        }
    }


}