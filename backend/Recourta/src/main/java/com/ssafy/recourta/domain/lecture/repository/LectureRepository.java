package com.ssafy.recourta.domain.lecture.repository;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    @Override
    Optional<Lecture> findById(Integer lectureId);

    @Override
    void deleteById(Integer lectureId);
}