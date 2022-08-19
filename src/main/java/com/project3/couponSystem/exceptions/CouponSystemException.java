package com.project3.couponSystem.exceptions;

public class CouponSystemException extends Exception {
    public CouponSystemException(ErrMsg errMsg ) {
        super(errMsg.getMsg());
    }
}
