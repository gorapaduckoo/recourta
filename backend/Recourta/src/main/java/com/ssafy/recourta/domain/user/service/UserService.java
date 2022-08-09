package com.ssafy.recourta.domain.user.service;


import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import com.ssafy.recourta.domain.session.repository.SessionRepository;
import com.ssafy.recourta.domain.user.dto.request.UserRequest;
import com.ssafy.recourta.domain.user.dto.response.UserResponse;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.ssafy.recourta.global.util.UserUtil.uploadImage;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private final LectureRepository lectureRepository;

    @Autowired
    private final SessionRepository sessionRepository;

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

        // 회원 탈퇴 진행 전, 회원이 가르칠 예정이었던 강의들을 삭제 또는 종강처리
        List<Lecture> lectures = lectureRepository.findAllByUser_UserIdAndStartDateAfter(userId, LocalDate.now().minusDays(1));
        for (Lecture l : lectures) {
            Integer cnt = sessionRepository.countByLecture_LectureIdAndStartTimeBefore(l.getLectureId(), LocalDateTime.now());
            if (cnt == 0) { // 만약 아직 시작되지 않은 강의라면 강의 삭제 처리
                lectureRepository.deleteById(l.getLectureId());
            } else { // 한번이라도 진행했던 강의라면 강의 종강 처리
                // 강의 종강일 업데이트 & 강의자 null 처리
                l.update(l.getContent(), l.getStartDate(), LocalDate.now(), l.getLectureImg(), l.getLectureTime(), null);
                lectureRepository.save(l);
                // 현재 시간 이후 세션 삭제 처리
                sessionRepository.deleteAllByLecture_LectureIdAndStartTimeAfter(l.getLectureId(), LocalDateTime.now());
            }
        }

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(userId);


        return UserResponse.OnlyId.build(user);
    }

    public User doLogin(UserRequest.Dologin request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);

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
