package com.project3.couponSystem.services;

import com.project3.couponSystem.beans.Category;
import com.project3.couponSystem.beans.Company;
import com.project3.couponSystem.beans.Coupon;
import com.project3.couponSystem.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {
    Company GetCompanyByEmail(String email);
    boolean login(String email, String password) throws CouponSystemException;

    Coupon addCoupon(int companyId ,Coupon coupon) throws CouponSystemException;

    Coupon updateCoupon(int companyId,int couponId,Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int companyId,int id) throws CouponSystemException;

    List<Coupon> getCoupons(int companyId);

    List<Coupon> getCouponsByCategory(int companyId,Category category);

    List<Coupon> getCouponsByMaxPrice(int companyId,double price);

    Company getDetails(int companyId);

}
