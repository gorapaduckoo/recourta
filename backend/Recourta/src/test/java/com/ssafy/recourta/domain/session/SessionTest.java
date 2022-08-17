package com.ssafy.recourta.domain.session;

import com.ssafy.recourta.domain.session.service.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SessionTest {

    @Autowired
    SessionService sessionService;

  //  @Test
    public void getEarliestAvailableSessionTest() {
        Integer lectureId = 3;
        assertThat(sessionService.getEarliestAvailableSession(lectureId)).isEqualTo(6);
    }
}
