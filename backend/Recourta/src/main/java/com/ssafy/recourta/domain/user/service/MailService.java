package com.ssafy.recourta.domain.user.service;


import com.ssafy.recourta.domain.user.dto.request.UserRequest;
import com.ssafy.recourta.domain.user.dto.response.UserResponse;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import com.ssafy.recourta.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    RedisUtil redisUtil;

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private MimeMessage createMessage(String email) throws Exception{
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject("Recourta 인증번호입니다.");

        String  code = UUID.randomUUID().toString().replaceAll("-","").substring(0, 9);

        redisUtil.setDataExpire(code,email,60*5L);

        String msg="";
        msg+= "<div style='margin:100px;'>";
        msg+= "<h1> 안녕하세요 Recourta입니다. </h1>";
        msg+= "<br>";
        msg+= "<p>아래 인증코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msg+= "<br>";
        msg+= "<p>감사합니다.<p>";
        msg+= "<br>";
        msg+= "<div align='center' style='border:1px solid black; font-family:verdana';><br>";
        msg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msg+= "<div style='font-size:130%'>";
        msg+= "CODE : <strong>";
        msg+= code+"</strong><div><br/> ";
        msg+= "</div>";

        //message.setText("이메일 인증코드: "+code, "utf-8", "html");
        message.setText(msg, "utf-8", "html");

        message.setFrom(new InternetAddress("2753dudwns@naver.com","리코타"));

        return  message;
    }

    private MimeMessage createPwMessage(String email) throws Exception{
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject("Recourta 인증번호입니다.");

        String  code = UUID.randomUUID().toString().replaceAll("-","").substring(0, 9);

        redisUtil.setDataExpire(code,email,60*5L);

        String msg="";
        msg+= "<div style='margin:100px;'>";
        msg+= "<h1> 안녕하세요 Recourta입니다. </h1>";
        msg+= "<br>";
        msg+= "<p>아래 [비밀번호 변경하기]를 눌러 새로운 비밀번호를 설정해주세요.<p>";
        msg+= "<br>";
        msg+= "<p>링크는 5분간 유효합니다.<p>";
        msg+= "<br>";
        msg+= "<p>감사합니다.<p>";
        msg+= "<br>";
        msg+= "<div align='center' style='border:1px solid black; font-family:verdana';><br>";
        msg+= "<h3 style='color:blue;'><a href='http://localhost:5173/recourta/reset?code=" +
                code+"'>[비밀번호 변경하기]</a></h3>";
        msg+= "<br/> ";
        msg+= "</div>";

        //message.setText("이메일 인증코드: "+code, "utf-8", "html");
        message.setText(msg, "utf-8", "html");

        message.setFrom(new InternetAddress("2753dudwns@naver.com","리코타"));

        return  message;
    }

    public void sendMail(String email) throws Exception{
        try{
            MimeMessage mimeMessage = createMessage(email);


            javaMailSender.send(mimeMessage);
        }catch (MailException mailException){
            mailException.printStackTrace();
            throw   new IllegalAccessException();
       }
    }

    public void sendPwMail(String email) throws Exception{
        try{
            MimeMessage mimeMessage = createPwMessage(email);


            javaMailSender.send(mimeMessage);
        }catch (MailException mailException){
            mailException.printStackTrace();
            throw   new IllegalAccessException();
        }
    }

    public UserResponse.isSuccess registMail(String email) throws  Exception{


        Optional<User> user = userRepository.findByEmail(email);

        if(!user.isPresent()){

            sendMail(email);
            return UserResponse.isSuccess.build(true);
        }


        return UserResponse.isSuccess.build(false);
    }

    public UserResponse.isSuccess resetMail(String email) throws  Exception{


        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()){

            sendPwMail(email);
            return UserResponse.isSuccess.build(true);
        }


        return UserResponse.isSuccess.build(false);
    }

    public String codeCheck(UserRequest.CodeCheck request){

        if(redisUtil.getData(request.getCode()) == null){
            return "expired";
        }

        else if(redisUtil.getData(request.getCode()).equals(request.getEmail())) {
            return "success";

        }else{
            return  "fail";
        }
    }

    public  String resetPw(UserRequest.ResetPw request){

        if(redisUtil.getData(request.getCode()) == null){
            return  "expired";
        }

        User user = userRepository.findByEmail(redisUtil.getData(request.getCode())).orElseThrow(UserNotFoundException::new);
        user.setPassword(passwordEncoder.encode(request.getNewPw()));
        userRepository.save(user);
        return "success";
    }
}
