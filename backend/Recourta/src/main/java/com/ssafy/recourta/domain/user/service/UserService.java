package com.ssafy.recourta.domain.user.service;


import com.ssafy.recourta.domain.user.dto.request.UserRequest;
import com.ssafy.recourta.domain.user.dto.response.UserResponse;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserResponse.OnlyId create(UserRequest.Create request){
        User user = request.create();
        User savedUser = userRepository.save(user);
        return UserResponse.OnlyId.build(savedUser);

    }

    public UserResponse.Search search(int userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return UserResponse.Search.build(user);
    }


    public UserResponse.OnlyId update(int userId, UserRequest.Update request){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setPassword(request.getPassword());
        user.setUserImg(request.getUserImg());

        User savedUser = userRepository.save(user);
        return UserResponse.OnlyId.build(savedUser);
    }

    @Transactional
    public UserResponse.OnlyId delete(int userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(userId);
        return UserResponse.OnlyId.build(user);
    }
}
