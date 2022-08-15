package com.ssafy.recourta.domain.checkinout.Controller;

import com.ssafy.recourta.domain.checkinout.dto.request.CheckInOutRequest;
import com.ssafy.recourta.domain.checkinout.dto.response.CheckInOutResponse;
import com.ssafy.recourta.domain.checkinout.service.CheckInOutService;
import com.ssafy.recourta.global.exception.CheckInOutException;
import com.ssafy.recourta.global.exception.SessionNotFoundException;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import com.ssafy.recourta.global.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check")
public class CheckInOutController {

    @Autowired
    private CheckInOutService checkInOutService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/in")
    public ResponseEntity<CheckInOutResponse.CheckId> checkIn(@RequestBody CheckInOutRequest.CheckInInfo request, @RequestHeader(value="Authorization") String accessToken) {
        int userId = request.getUserId();
        int tokenUserId = Integer.parseInt(jwtTokenUtil.getUserIdFromToken(accessToken));

        if(userId != tokenUserId) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        try {
            CheckInOutResponse.CheckId result = checkInOutService.checkIn(request);
            return ResponseEntity.ok().body(result);
        } catch(UserNotFoundException e) {
            return ResponseEntity.internalServerError().build();
        } catch(SessionNotFoundException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/out")
    public ResponseEntity<CheckInOutResponse.CheckId> checkOut(@RequestBody CheckInOutRequest.CheckOutInfo request, @RequestHeader(value="Authorization") String accessToken) {
        int userId = request.getUserId();
        int tokenUserId = Integer.parseInt(jwtTokenUtil.getUserIdFromToken(accessToken));

        if(userId != tokenUserId) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        try {
            checkInOutService.checkOut(request);
            return ResponseEntity.ok().build();
        } catch(UserNotFoundException e) {
            return ResponseEntity.internalServerError().build();
        } catch(SessionNotFoundException e) {
            return ResponseEntity.internalServerError().build();
        } catch(CheckInOutException.InvalidCheckInOutData e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
