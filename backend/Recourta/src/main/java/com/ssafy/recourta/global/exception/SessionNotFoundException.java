package com.ssafy.recourta.global.exception;

import javax.persistence.EntityNotFoundException;

public class SessionNotFoundException extends EntityNotFoundException {
    public SessionNotFoundException() {
        super("존재하지 않는 수업입니다.");
    }
}
