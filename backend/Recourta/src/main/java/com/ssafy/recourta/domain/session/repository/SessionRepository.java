package com.ssafy.recourta.domain.session.repository;

import com.ssafy.recourta.domain.session.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Integer> {

}
