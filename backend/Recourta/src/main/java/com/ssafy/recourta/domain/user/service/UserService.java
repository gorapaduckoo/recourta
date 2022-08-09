package com.ssafy.recourta.domain.user.service;


import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import com.ssafy.recourta.domain.user.dto.request.UserRequest;
import com.ssafy.recourta.domain.user.dto.response.UserResponse;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
import com.ssafy.recourta.global.exception.LectureException;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ssafy.recourta.global.util.UserUtil.uploadImage;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private LectureRepository lectureRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 파일을 업로드할 경로를 application.properties 파일에서 가져옴
    @Value("${com.ssafy.recourta.upload.path}")
    private String uploadPath;


    public UserResponse.OnlyId create(UserRequest.Create request, MultipartFile userImg) throws Exception {
        User user = request.create();
        if(user==null){
            throw new UserNotFoundException();
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        /////////////////// 이미지 처리 파트 ////////////////////
        uploadImage(user, userImg);

        User savedUser = userRepository.save(user);
        return UserResponse.OnlyId.build(savedUser);

    }

    public UserResponse.Search search(int userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return UserResponse.Search.build(user);
    }



    public UserResponse.isSuccess updateImg(UserRequest.UpdateImg request, MultipartFile userImg) throws Exception {
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
        uploadImage(user, userImg);
        User savedUser = userRepository.save(user);
        return UserResponse.isSuccess.build(true);
    }

    public UserResponse.isSuccess updatePw(UserRequest.UpdatePw request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);


        if(passwordEncoder.matches(request.getNowPw(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(request.getNewPw()));
            userRepository.save(user);
            return UserResponse.isSuccess.build(true);
        }
        return UserResponse.isSuccess.build(false);
    }

    @Transactional
    public UserResponse.OnlyId delete(int userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if(user.getIsStudent() == 0){
            // 수강 시작 전인 강의
            List<Lecture> notstart = lectureRepository.findAllByUser_UserIdAndStartDateAfter(user.getUserId(), LocalDate.now().minusDays(1));
            for(Lecture l : notstart){
                lectureRepository.deleteById(l.getLectureId());
            }

            // 현재 수강중인 강의
            List<Lecture> unfinished = lectureRepository.findAllByUser_UserIdAndStartDateBeforeAndEndDateAfter(user.getUserId(), LocalDate.now().plusDays(1),LocalDate.now().minusDays(1));
            for(Lecture l : unfinished ){
                l.setEndDate(LocalDate.now());
                lectureRepository.save(l);
            }
        }

        userRepository.deleteById(userId);
        return UserResponse.OnlyId.build(user);
    }

    public User doLogin(UserRequest.Dologin request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);
        System.out.println("유저서비스"+user);

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
//        if(!passwordEncoder.matches(user.getPassword(), passwordEncoder.encode(request.getPassword()))) {
            throw new UserNotFoundException();
        }

        return user;
    }

    public User getUser(int userId) {
        return userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }


}
