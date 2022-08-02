package com.ssafy.recourta.domain.user.controller;


import com.ssafy.recourta.domain.user.dto.request.UserRequest;
import com.ssafy.recourta.domain.user.dto.response.UserResponse;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.service.MailService;
import com.ssafy.recourta.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MailService mailService;


    @PostMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    // @RequestBody는 JSON형태의 바디로 들어오는 데이터들을 파싱해 줌
    // Multipart/form-data로 전달되는 컨텐츠는 RequestBody 사용 시 Exception 발생 => @RequestPart 사용
    public ResponseEntity<UserResponse.OnlyId> create(@RequestPart("request") UserRequest.Create request, @RequestPart("userImg") MultipartFile userImg) throws Exception {


        UserResponse.OnlyId response = userService.create(request, userImg);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/user/{userId}")
    public  ResponseEntity<UserResponse.Search> search(@PathVariable int userId){
        UserResponse.Search response = userService.search(userId);
        return  ResponseEntity.ok().body(response);
    }

    @PutMapping("/user/img")
    public  ResponseEntity<UserResponse.isSuccess> updateImg(@RequestPart("request") UserRequest.UpdateImg request, @RequestPart("userImg") MultipartFile userImg) throws Exception {
        UserResponse.isSuccess response = userService.updateImg(request, userImg);
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

    @PostMapping(value = "/auth/regist")
    public ResponseEntity<UserResponse.isSuccess> regMail(@RequestBody String data) throws Exception{
        JSONObject parser = new JSONObject(data);
        String email = parser.getString("email");
        // 조건 email null 일 때

       UserResponse.isSuccess response = mailService.registMail(email);

       if(response.isSuccess()){
           return ResponseEntity.ok().body(response);
       }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    public  ResponseEntity login(@RequestBody UserRequest.Dologin request){
        String loginInfo = userService.doLogin(request);
        if(loginInfo.equals("fail")){
            return ResponseEntity.badRequest().body(loginInfo);
        }

        return ResponseEntity.ok().body(loginInfo);
    }
}
