package com.ssafy.recourta.domain.user.repository;

import com.ssafy.recourta.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


}
