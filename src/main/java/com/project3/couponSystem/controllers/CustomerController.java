package com.project3.couponSystem.controllers;

import com.project3.couponSystem.Repository.CouponRepository;
import com.project3.couponSystem.beans.Category;
import com.project3.couponSystem.beans.Coupon;
import com.project3.couponSystem.beans.Customer;
import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.exceptions.SecurityException;
import com.project3.couponSystem.security.TokenManager;
import com.project3.couponSystem.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

    public class CustomerController extends ClientController  {
    private final CustomerService customerService;
    private final CouponRepository couponRepository;
    private final TokenManager tokenManager;
    @Override
    @PostMapping
    public boolean login(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        return customerService.login(email, password);
    }
    @PostMapping ("/coupon")
    @ResponseStatus(HttpStatus.CREATED)
    public Coupon purchaseCoupon(@RequestParam int couponId, @RequestHeader("Authorization") UUID token) throws CouponSystemException, SecurityException {
       int userId=tokenManager.getUserId(token);
       return customerService.purchaseCoupon(userId,couponId);
    }
    @GetMapping("/coupons")
    public ArrayList<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException, SecurityException {
        List<Coupon> coupons= customerService.getCustomersCoupons(tokenManager.getUserId(token));
        return (ArrayList<Coupon>) coupons;
    }

    @GetMapping("/details")
    public Customer getCustomerDetails(@RequestHeader("Authorization") UUID token) throws SecurityException {
        Customer customer= customerService.getDetails(tokenManager.getUserId(token));
    return customer;
    }
    @GetMapping("/store")
    public List<Coupon> getAllCoupons(@RequestHeader("Authorization") UUID token) throws SecurityException {
        tokenManager.getUserId(token);
        return couponRepository.getAllCoupons();
    }
}
