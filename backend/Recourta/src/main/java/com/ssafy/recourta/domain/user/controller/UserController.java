package com.ssafy.recourta.domain.user.controller;


import com.ssafy.recourta.domain.user.dto.request.UserRequest;
import com.ssafy.recourta.domain.user.dto.response.UserResponse;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/user")
    public ResponseEntity<UserResponse.OnlyId> create(@RequestBody UserRequest.Create request){
        UserResponse.OnlyId response = userService.create(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/user/{userId}")
    public  ResponseEntity<UserResponse.Search> search(@PathVariable int userId){
        UserResponse.Search response = userService.search(userId);
        return  ResponseEntity.ok().body(response);
    }

    @PutMapping("/user/img/{userId}")
    public  ResponseEntity<UserResponse.OnlyId> updateImg(@PathVariable int userId, @RequestBody UserRequest.UpdateImg request){
        UserResponse.OnlyId response = userService.updateImg(userId, request);
        return  ResponseEntity.ok().body(response);
    }


   @PutMapping("/user/pw")
   public ResponseEntity<UserResponse.isSuccess> updatePw(@RequestBody UserRequest.UpdatePw request){
        UserResponse.isSuccess response = userService.updatePw(request);
        if(response.isSuccess()){
            System.out.println(response.isSuccess());
            return ResponseEntity.ok().body(response);
        }

        return ResponseEntity.badRequest().body(response);

   }

    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<UserResponse.OnlyId> delete(@PathVariable int userId) {
        UserResponse.OnlyId response = userService.delete(userId);
        return ResponseEntity.ok().body(response);
    }
}
