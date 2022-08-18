package com.ssafy.recourta.domain.user.service;


import com.ssafy.recourta.domain.user.dto.request.UserRequest;
import com.ssafy.recourta.domain.user.dto.response.UserResponse;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import com.ssafy.recourta.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;
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
        message.setSubject("Recourta 회원가입 인증번호입니다.");

        // image
        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBodyPart = new MimeBodyPart();
        //

        String  code = UUID.randomUUID().toString().replaceAll("-","").substring(0, 9);

        redisUtil.setDataExpire(code,email,60*5L);

        String msg="";

        msg+= "<div align='center' style='width:75%; margin-left:auto; margin-right:auto; margin-bottom:30px;'>";
        msg+= "<br>";
        msg+= "<img style='width:480px;'   src=\"cid:image\">";
        msg+= "<br>";
        msg+= "<br>";
        msg+= "<h1> 안녕하세요 Recourta입니다. </h1>";
        msg+= "<br>";
        msg+= "<p>회원가입 창으로 돌아가 아래 인증코드를 5분 안에 입력해주세요<p>";
        msg+= "<br>";
        msg+= "<p>감사합니다.<p>";
        msg+= "<br>";
        msg+= "<div align='center' style='width:480px; border:1px solid black; font-family:verdana;';><br>";
        msg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msg+= "<div style='font-size:130%'>";
        msg+= "CODE : <strong>";
        msg+= code+"</strong><div> ";
        msg+= "</div>";


       // message.setText(msg, "utf-8", "html");

        message.setFrom(new InternetAddress("2753dudwns@naver.com","리코타"));

        //image
        messageBodyPart.setContent(msg, "text/html; charset=UTF-8");
        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        javax.activation.DataSource fds = new FileDataSource(ResourceUtils.getFile("/image/logo.png"));

        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<image>");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        //

        return  message;
    }

    private MimeMessage createPwMessage(String email) throws Exception{
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject("Recourta 비밀번호 찾기 메일입니다.");

        // image
        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBodyPart = new MimeBodyPart();
        //

        String  code = UUID.randomUUID().toString().replaceAll("-","").substring(0, 9);

        redisUtil.setDataExpire(code,email,60*5L);

        String msg="";
        msg+= "<div align='center' style='width:75%; margin-left:auto; margin-right:auto; margin-bottom:30px;'>";
        msg+= "<br>";
        msg+= "<img style='width:480px;'   src=\"cid:image\">";
        msg+= "<br>";
        msg+= "<br>";
        msg+= "<h1> 안녕하세요 Recourta입니다. </h1>";
        msg+= "<br>";
        msg+= "<p>아래 [비밀번호 변경하기]를 눌러 새로운 비밀번호를 설정해주세요.<p>";
        msg+= "<br>";
        msg+= "<p>링크는 5분간 유효합니다.<p>";
        msg+= "<br>";
        msg+= "<p>감사합니다.<p>";
        msg+= "<br>";
        msg+= "<div align='center' style='width:480px; border:1px solid black; font-family:verdana;';><br>";
        msg+= "<h3 style='color:blue;'><a href='https://recourta.ga/recourta/reset?code=" +
                code+"'>[비밀번호 변경하기]</a></h3>";
        msg+= "<br/> ";
        msg+= "</div>";

        //message.setText("이메일 인증코드: "+code, "utf-8", "html");
        //message.setText(msg, "utf-8", "html");

        //image
        messageBodyPart.setContent(msg, "text/html; charset=UTF-8");
        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        javax.activation.DataSource fds = new FileDataSource(ResourceUtils.getFile("/image/logo.png"));

        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<image>");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        //

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
