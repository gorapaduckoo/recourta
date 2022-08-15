package com.ssafy.recourta.domain.checkinout.service;

import com.ssafy.recourta.domain.checkinout.dto.request.CheckInOutRequest;
import com.ssafy.recourta.domain.checkinout.dto.response.CheckInOutResponse;
import com.ssafy.recourta.domain.checkinout.entity.CheckInOut;
import com.ssafy.recourta.domain.checkinout.respository.CheckInOutRepository;
import com.ssafy.recourta.domain.session.entity.Session;
import com.ssafy.recourta.domain.session.repository.SessionRepository;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
import com.ssafy.recourta.global.exception.CheckInOutException;
import com.ssafy.recourta.global.exception.SessionNotFoundException;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CheckInOutServiceImpl implements CheckInOutService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    CheckInOutRepository checkInOutRepository;

    @Override
    public CheckInOutResponse.CheckId checkIn(CheckInOutRequest.CheckInInfo checkInInfo) {
        User user = userRepository.findByUserId(checkInInfo.getUserId()).orElseThrow(UserNotFoundException::new);
        Session session = sessionRepository.findById(checkInInfo.getSessionId()).orElseThrow(SessionNotFoundException::new);
        CheckInOut checkInOut = checkInInfo.toEntity(user, session);
        CheckInOut result = checkInOutRepository.save(checkInOut);
        return CheckInOutResponse.CheckId.builder().checkId(result.getId()).build();
    }

    @Override
    public void checkOut(CheckInOutRequest.CheckOutInfo checkOutInfo) {
        User user = userRepository.findByUserId(checkOutInfo.getUserId()).orElseThrow(UserNotFoundException::new);
        Session session = sessionRepository.findById(checkOutInfo.getSessionId()).orElseThrow(SessionNotFoundException::new);
        CheckInOut checkInOut = checkInOutRepository.findById(checkOutInfo.getCheckId()).orElseThrow(() -> new CheckInOutException.InvalidCheckInOutData());
        checkInOut.setExitTime(LocalDateTime.now());
        CheckInOut result = checkInOutRepository.save(checkInOut);
    }
}
