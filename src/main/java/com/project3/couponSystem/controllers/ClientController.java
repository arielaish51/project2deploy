package com.project3.couponSystem.controllers;

import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.front.LoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public abstract class ClientController {
//    private final LoginManager loginManager;

    public abstract boolean login(String email, String password) throws CouponSystemException;
}

