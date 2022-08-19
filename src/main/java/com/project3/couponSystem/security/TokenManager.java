package com.project3.couponSystem.security;

import com.project3.couponSystem.Repository.CompanyRepository;
import com.project3.couponSystem.Repository.CustomerRepository;
import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.exceptions.SecMsg;
import com.project3.couponSystem.exceptions.SecurityException;
import com.project3.couponSystem.front.ClientType;
import com.project3.couponSystem.services.AdminService;
import com.project3.couponSystem.services.CompanyService;
import com.project3.couponSystem.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenManager {
    private final Map<UUID, Information> map;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    public UUID add(String email, String password) throws CouponSystemException {
        Information information = new Information();
        information.setEmail(email);
        information.setTime(LocalDateTime.now());

        ClientType clientType = null;
        if (adminService.login(email, password)) {
            clientType = ClientType.Administrator;

        }
        if (companyService.login(email, password)) {
            clientType = ClientType.Company;
        }
        if (customerService.login(email, password)) {
            clientType = ClientType.Customer;
        }
        information.setClientType(clientType);
        switch (clientType) {
            case Company:
                information.setUserId(companyRepository.findByEmail(email).getId());
                break;
            case Customer:
                information.setUserId(customerRepository.findByEmail(email).getId());
                break;
            default:
                information.setUserId(1);
        }

        UUID token = UUID.randomUUID();
        map.put(token, information);
        return token;
    }

    public int getUserId(UUID token) throws SecurityException {

        Information information = map.get(token);
        if (information == null) {
            throw new SecurityException(SecMsg.INVALID_TOKEN);
        }
        return information.getUserId();
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void deleteExpiredTokenOver30Minutes() {
        map.entrySet().removeIf(ins -> ins.getValue().getTime().isAfter(LocalDateTime.now().plusMinutes(30)));
    }

    public boolean isAdminValid(UUID token) throws SecurityException {
        if (map.get(token)!=null){
            return true;
        }
        throw new SecurityException(SecMsg.INVALID_TOKEN);
   }


}
