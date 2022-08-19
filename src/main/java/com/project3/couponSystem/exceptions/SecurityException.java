package com.project3.couponSystem.exceptions;

import lombok.Data;

/**
 * Created by kobis on 19 May, 2022
 */
@Data
public class SecurityException extends Exception{

    private SecMsg secMsg;
    public SecurityException(SecMsg secMsg){
        super(secMsg.getMsg());
        this.secMsg = secMsg;
    }
}
