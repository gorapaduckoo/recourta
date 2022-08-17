package com.ssafy.recourta.domain.registration.repository;

import com.ssafy.recourta.domain.registration.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    /* List의 경우 없으면 빈 List를 리턴하므로 Optional 처리를 해줄 필요가 없다. */

    // 유저가 듣는 강의 목록
    List<Registration> findAllByUserUserId(Integer userId);
    // 해당 강의를 듣는 유저 목록
    List<Registration> findAllByLectureLectureId(Integer lectureId);
    // 수강 철회
    void deleteByUserUserIdAndLectureLectureId(Integer userId, Integer lectureId);
    // id 찾기
    Optional<Registration> findByUserUserIdAndLectureLectureId(Integer userId, Integer lectureId);
}
