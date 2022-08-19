package com.project3.couponSystem.services;

import com.project3.couponSystem.Repository.CompanyRepository;
import com.project3.couponSystem.Repository.CouponRepository;
import com.project3.couponSystem.Repository.CustomerRepository;
import com.project3.couponSystem.exceptions.CouponSystemException;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CompanyRepository companyRepository;
    public abstract boolean login(String email,String password) throws CouponSystemException;
}
