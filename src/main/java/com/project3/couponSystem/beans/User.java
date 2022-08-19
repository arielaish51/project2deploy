package com.project3.couponSystem.beans;

import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.front.ClientType;
import com.project3.couponSystem.services.AdminService;
import com.project3.couponSystem.services.CompanyService;
import com.project3.couponSystem.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@Service
@AllArgsConstructor
@NoArgsConstructor
public class User {



    private  ClientType clientType;
    @Email
    private  String email;
    private  String password;
    private int id;
    public User(String email,String password){
        this.email=email;
        this.password=password;
    }
}
