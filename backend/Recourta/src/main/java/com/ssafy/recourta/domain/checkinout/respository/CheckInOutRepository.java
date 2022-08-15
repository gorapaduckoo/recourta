package com.ssafy.recourta.domain.checkinout.respository;

import com.ssafy.recourta.domain.checkinout.entity.CheckInOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInOutRepository extends JpaRepository<CheckInOut, Long> {

}
