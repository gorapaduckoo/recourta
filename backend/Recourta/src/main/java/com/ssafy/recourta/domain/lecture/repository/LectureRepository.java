package com.ssafy.recourta.domain.lecture.repository;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    @Override
    Optional<Lecture> findById(Integer lectureId);

    @Override
    void deleteById(Integer lectureId);


    // startDate <= 현재날짜 면서 현재날짜 <= endDate인 강의 찾기
    List<Lecture> findAllByUser_UserIdAndStartDateBeforeAndEndDateAfter(Integer userId, LocalDate tomorrow, LocalDate yesterday);
}