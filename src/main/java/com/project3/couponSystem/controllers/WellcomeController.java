package com.project3.couponSystem.controllers;


import com.project3.couponSystem.beans.User;
import com.project3.couponSystem.dto.LoginResDto;
import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.exceptions.SecurityException;
import com.project3.couponSystem.front.ClientType;
import com.project3.couponSystem.services.AdminService;
import com.project3.couponSystem.services.CompanyService;
import com.project3.couponSystem.services.CustomerService;
import com.project3.couponSystem.services.WelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/welcome/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class WellcomeController {
    private final WelcomeService welcomeService;
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;
    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestParam String email, @RequestParam  String password,@RequestParam String name, @RequestParam ClientType clientType) throws CouponSystemException, SecurityException {
        welcomeService.register(email, password,name, clientType);
    }

    @PostMapping("login")
    public LoginResDto login(@Valid @RequestBody User user) throws CouponSystemException, SecurityException {
        ClientType clientType = null;
        String email = user.getEmail();
        String password = user.getPassword();
        UUID token = welcomeService.login(email, password);
        if (adminService.login(email, password)) {
            clientType = ClientType.Administrator;
        }
        if (companyService.login(email, password)) {
            clientType = ClientType.Company;
        }
        if (customerService.login(email, password)) {
            clientType = ClientType.Customer;
        }
        return new LoginResDto(clientType, email, token);
    }
}
