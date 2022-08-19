package com.project3.couponSystem.beans;

import com.project3.couponSystem.front.ClientType;

import javax.persistence.Entity;

public abstract class Users {
    protected ClientType clientType;

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}
