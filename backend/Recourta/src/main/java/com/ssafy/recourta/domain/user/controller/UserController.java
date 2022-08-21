package com.ssafy.recourta.domain.user.controller;


import com.ssafy.recourta.domain.auth.service.AuthService;
import com.ssafy.recourta.domain.user.dto.request.UserRequest;
import com.ssafy.recourta.domain.user.dto.response.UserResponse;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.service.MailService;
import com.ssafy.recourta.domain.user.service.UserService;
import com.ssafy.recourta.domain.auth.dto.TokenDto;
import com.ssafy.recourta.global.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

     private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final MailService mailService;

    private final AuthService authService;

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    // @RequestBody는 JSON형태의 바디로 들어오는 데이터들을 파싱해 줌
    // Multipart/form-data로 전달되는 컨텐츠는 RequestBody 사용 시 Exception 발생 => @RequestPart 사용
    public ResponseEntity<UserResponse.OnlyId> create(@Valid @RequestPart("request") UserRequest.Create request, @RequestPart("userImg") MultipartFile userImg) throws Exception {
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

//   @PutMapping("/user/pw")
//   public ResponseEntity<UserResponse.isSuccess> updatePw(@Valid @RequestBody UserRequest.UpdatePw request){
//        UserResponse.isSuccess response = userService.updatePw(request);
//        if(response.isSuccess()){
//            System.out.println("3"+response.isSuccess());
//            System.out.println("4"+response);
//            return ResponseEntity.ok().body(response);
//        }
//       System.out.println("1"+response.isSuccess());
//       System.out.println("2"+response);
//        return ResponseEntity.badRequest().body(response);
//
//   }

    @PutMapping("/user/pw")
    public ResponseEntity updatePw(@Valid @RequestBody UserRequest.UpdatePw request){
        String response = userService.updatePw(request);
        if(response.equals("success")){
            System.out.println(response);
            return ResponseEntity.ok().body(response);
        }
        System.out.println(response);
        return ResponseEntity.badRequest().body(response);

    }

    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<UserResponse.OnlyId> delete(@PathVariable int userId)  throws Exception{
         logger.info("controller in");
        UserResponse.OnlyId response = userService.delete(userId);
         logger.info("controller out");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/auth/regist")
    public ResponseEntity<UserResponse.isSuccess> regMail(@Valid @RequestBody String data) throws Exception{
        JSONObject parser = new JSONObject(data);
        String email = parser.getString("email");
        // 조건 email null 일 때

       UserResponse.isSuccess response = mailService.registMail(email);

       if(response.isSuccess()){
           return ResponseEntity.ok().body(response);
       }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping(value = "/reset")
    public ResponseEntity<UserResponse.isSuccess> resetMail(@Valid @RequestBody String data) throws Exception{
        JSONObject parser = new JSONObject(data);
        String email = parser.getString("email");
        // 조건 email null 일 때

        UserResponse.isSuccess response = mailService.resetMail(email);

        if(response.isSuccess()){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserRequest.Dologin request) {
        User user = userService.doLogin(request);

        TokenDto.Refresh refresh = authService.createTokens(user);

        return ResponseEntity.ok().body(refresh);
    }

    @PostMapping("/auth/code")
    public ResponseEntity regist(@Valid @RequestBody UserRequest.CodeCheck request){
        String registInfo = mailService.codeCheck(request);
        if(registInfo.equals("fail")){
            return ResponseEntity.badRequest().body(registInfo);
        }else if(registInfo.equals("expired")){
            return ResponseEntity.badRequest().body(registInfo);
        }
        else{
            return ResponseEntity.ok().body(registInfo);
        }
    }

    @PutMapping("/reset/pw")
    public ResponseEntity resetPw(@Valid @RequestBody UserRequest.ResetPw request){
        String resetInfo = mailService.resetPw(request);
        if(resetInfo.equals("expired")){
            return  ResponseEntity.badRequest().body(resetInfo);
        }

        return ResponseEntity.ok().body(resetInfo);
    }
}
