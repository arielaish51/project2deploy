package com.project3.couponSystem.controllers;

import com.project3.couponSystem.beans.Company;
import com.project3.couponSystem.beans.Customer;
import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.exceptions.SecMsg;
import com.project3.couponSystem.exceptions.SecurityException;
import com.project3.couponSystem.security.TokenManager;
import com.project3.couponSystem.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders="*")
public class AdminController extends ClientController {
    private final AdminService adminService;
    private final TokenManager tokenManager;


    @Override
    @PostMapping
    public boolean login(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        return adminService.login(email, password);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/company")
    public Company addCompany(@RequestHeader("Authorization") UUID token,@Valid @RequestBody Company company) throws CouponSystemException, SecurityException {
        if (!tokenManager.isAdminValid(token)){
            throw new SecurityException(SecMsg.INVALID_TOKEN);
        }
        return  adminService.addCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@RequestHeader("Authorization") UUID token,@PathVariable int id, @Valid@RequestBody Company company) throws CouponSystemException, SecurityException {

        if (!tokenManager.isAdminValid(token)) {
           throw new SecurityException(SecMsg.INVALID_TOKEN);
        }
        company.setId(id);
        return adminService.updateCompany(company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@RequestHeader("Authorization") UUID token,@PathVariable int id) throws CouponSystemException, SecurityException {
        if (!tokenManager.isAdminValid(token)) {
            throw new SecurityException(SecMsg.INVALID_TOKEN);

        }
        adminService.deleteCompany(id);
    }
    @GetMapping("/Companies")
    public ArrayList<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) throws SecurityException
    {
        if (!tokenManager.isAdminValid(token)) {
            throw new SecurityException(SecMsg.INVALID_TOKEN);

        }
        return (ArrayList<Company>) adminService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getOneCompany(@RequestHeader("Authorization") UUID token,@PathVariable int id) throws CouponSystemException, SecurityException {
        if (!tokenManager.isAdminValid(token)) {
            throw new SecurityException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getSingleCompany(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/Customer")
    public Customer addCustomer(@RequestHeader("Authorization") UUID token,@Valid@RequestBody Customer customer) throws CouponSystemException, SecurityException {
        if (!tokenManager.isAdminValid(token)) {
            throw new SecurityException(SecMsg.INVALID_TOKEN);
        }
        return adminService.addCustomer(customer);
    }
    @PutMapping("/Customer/{id}")
    public Customer updateCustomer(@RequestHeader("Authorization") UUID token,@PathVariable int id,@Valid @RequestBody Customer customer) throws CouponSystemException, SecurityException {
        if (!tokenManager.isAdminValid(token)) {
            throw new SecurityException(SecMsg.INVALID_TOKEN);
        }
        customer.setId(id);
        return adminService.updateCustomer(customer);
    }
    @DeleteMapping("/Customer/{id}")
    public void deleteCustomer(@RequestHeader("Authorization") UUID token,@PathVariable int id) throws CouponSystemException, SecurityException {
        if (!tokenManager.isAdminValid(token)) {
            throw new SecurityException(SecMsg.INVALID_TOKEN);
        }
        adminService.deleteCustomer(id);
    }
    @GetMapping("/Customers")
    public List<Customer> getAllCustomer(@RequestHeader("Authorization") UUID token) throws SecurityException {
        if (!tokenManager.isAdminValid(token)) {
            throw new SecurityException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getCustomers();
    }
    @GetMapping("/Customer/{id}")
    public Customer getCustomer(@RequestHeader("Authorization") UUID token,@PathVariable int id) throws CouponSystemException, SecurityException {
        return tokenManager.isAdminValid(token) ? adminService.getCustomer(id):null;
    }

}
