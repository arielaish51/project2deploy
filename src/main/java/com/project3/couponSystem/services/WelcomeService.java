package com.project3.couponSystem.services;

import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.exceptions.SecurityException;
import com.project3.couponSystem.front.ClientType;

import java.util.UUID;

public interface WelcomeService {
    void register(String email,String password,String name, ClientType clientType) throws SecurityException, CouponSystemException;
    UUID login(String email, String password) throws SecurityException, CouponSystemException;
}
