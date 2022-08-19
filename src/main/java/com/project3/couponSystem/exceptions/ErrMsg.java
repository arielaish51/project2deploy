package com.project3.couponSystem.exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {
    AddingParametersException("adding parameters method ahs interrupted"),
    ConnectionException(" connection has interrupted"),
    DontExistException("can't find the data "),
    DontExistException_FOR_COMPANY("can't find the company "),
    ExceptionMail("The mail you entered is incorrect"),
    ExecutionException(" the execution has interrupted"),
    NAME_ALREADY_EXIST("you cant add the item, the name already exist"),
    EMAIL_ALREADY_EXIST("you cant add the item, the email already exist"),
    COUPON_ALREADY_EXIST("you cant add the item, the coupon already exist"),
    CANT_CHANGE_PASSWORD("you cant change the customer/company password"),
    CANT_CHANGE_NAME("you cant change the company name"),
    CANT_CHANGE_COMPANY_ID("you cant change the company id"),
    ONLY_ONE_PURCHASE("you can buy only one time the coupon"),
    COUPON_OUT_OF_STOCK("the coupon stock is over"),
    COUPON_EXPIRED("the coupon has been expired");

    private String msg;

    ErrMsg(String msg) {
        this.msg = msg;
    }

}
