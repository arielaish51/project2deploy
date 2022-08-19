package com.project3.couponSystem.services;

import com.project3.couponSystem.beans.Company;
import com.project3.couponSystem.beans.Customer;
import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.exceptions.SecMsg;
import com.project3.couponSystem.exceptions.SecurityException;
import com.project3.couponSystem.front.ClientType;
import com.project3.couponSystem.security.TokenManager;
import com.project3.couponSystem.front.LoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class WelcomeServiceImpl implements WelcomeService {
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final TokenManager tokenManager;
    private final LoginManager loginManager;


    @Override
    public void register(String email, String password,String name, ClientType clientType) throws SecurityException, CouponSystemException {
        AdminService adminService1 = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
        switch (clientType){
            case Company:{
                adminService1.addCompany(Company.builder().name(name).email(email).password(password).build());
                break;}
            case Customer:{
                adminService1.addCustomer(Customer.builder().email(email).firstName(name).password(password).build());
            }

        }
    }

    @Override
    public UUID login(String email, String password) throws SecurityException, CouponSystemException {
        if (!adminService.login(email, password) && !companyService.login(email, password) && !customerService.login(email, password)) {
            throw new SecurityException(SecMsg.EMAIL_OR_PASSWORD_INCORRECT);
        }
        UUID token = tokenManager.add(email, password);
        return token;
    }
}
