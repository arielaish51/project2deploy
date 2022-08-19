package com.project3.couponSystem.controllers;

import com.project3.couponSystem.beans.Category;
import com.project3.couponSystem.beans.Company;
import com.project3.couponSystem.beans.Coupon;
import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.exceptions.SecurityException;
import com.project3.couponSystem.security.TokenManager;
import com.project3.couponSystem.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/company")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CompanyController extends ClientController {
    private final CompanyService companyService;
    private final TokenManager tokenManager;

    @Override
    @PostMapping
    public boolean login(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        return companyService.login(email, password);
    }

    @PostMapping("/coupon")
    @ResponseStatus(HttpStatus.CREATED)
    public Coupon addCoupon(@RequestHeader("Authorization") UUID token,@Valid @RequestBody Coupon coupon) throws CouponSystemException, SecurityException {
       return companyService.addCoupon(tokenManager.getUserId(token),coupon);
    }

    @PutMapping("/{id}")
    public Coupon updateCoupon(@RequestHeader("Authorization") UUID token,@PathVariable int id ,@RequestBody Coupon coupon) throws CouponSystemException, SecurityException {
       return companyService.updateCoupon(tokenManager.getUserId(token),id,coupon);
    }

    @DeleteMapping("/{id}")
    public void deleteCoupon(@RequestHeader("Authorization") UUID token,@PathVariable int id) throws CouponSystemException, SecurityException {
        companyService.deleteCoupon(tokenManager.getUserId(token),id);
    }

    @GetMapping("/coupons")
    public ArrayList<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token) throws SecurityException {
        List<Coupon> coupons = companyService.getCoupons(tokenManager.getUserId(token));
        return (ArrayList<Coupon>) coupons;
    }
//redundant
//    @GetMapping("/coupons/{category}")
//    public ArrayList<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token,@PathVariable Category category) throws SecurityException {
//        List<Coupon> coupons = companyService.getCouponsByCategory(tokenManager.getUserId(token),category);
//        return (ArrayList<Coupon>) coupons;
//    }
//
//    @GetMapping("/MaxPrice")
//    public ArrayList<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token,@RequestParam double maxPrice) throws SecurityException {
//        List<Coupon> coupons = companyService.getCouponsByMaxPrice(tokenManager.getUserId(token),maxPrice);
//        return (ArrayList<Coupon>) coupons;
//    }

    @GetMapping("/details")
    public Company getCompanyDetails(@RequestHeader("Authorization") UUID token) throws SecurityException {
         Company company=companyService.getDetails(tokenManager.getUserId(token));
         return company;
    }


}
