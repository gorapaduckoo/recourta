package com.ssafy.recourta.domain.checkinout.service;


import com.ssafy.recourta.domain.checkinout.dto.request.CheckInOutRequest;
import com.ssafy.recourta.domain.checkinout.dto.response.CheckInOutResponse;

public interface CheckInOutService {

    CheckInOutResponse.CheckId checkIn(CheckInOutRequest.CheckInInfo checkInInfo);
    void checkOut(CheckInOutRequest.CheckOutInfo checkOutInfo);
}
