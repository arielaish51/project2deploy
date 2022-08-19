package com.project3.couponSystem.front;

import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.exceptions.ErrMsg;
import com.project3.couponSystem.services.AdminService;
import com.project3.couponSystem.services.ClientService;
import com.project3.couponSystem.services.CompanyService;
import com.project3.couponSystem.services.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class LoginManager {

    private final AdminService adminService;

    private final CompanyService companyService;

    private final CustomerService customerService;


    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        ClientService clientService = null;


        switch (clientType) {
            case Administrator: {
                clientService = (ClientService) adminService;
                break;
            }
            case Company: {
                clientService = (ClientService) companyService;
                break;
            }
            case Customer: {
                clientService = (ClientService) customerService;
                break;
            }

        }
        if (!clientService.login(email, password))
            throw new CouponSystemException(ErrMsg.DontExistException);
        return clientService;
    }

}