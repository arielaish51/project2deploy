package com.project3.couponSystem.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by kobis on 19 May, 2022
 */
@Getter
public enum SecMsg {

    EMAIL_ALREADY_EXIST("email already exist", HttpStatus.CONFLICT),
    EMAIL_OR_PASSWORD_INCORRECT("email or password incorrect", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("invalid token please login again", HttpStatus.UNAUTHORIZED);

    private String msg;
    private HttpStatus status;

    SecMsg(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
    }
}
