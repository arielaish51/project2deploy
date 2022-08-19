package com.project3.couponSystem.services;

import com.project3.couponSystem.beans.Category;
import com.project3.couponSystem.beans.Coupon;
import com.project3.couponSystem.beans.Customer;
import com.project3.couponSystem.exceptions.CouponSystemException;

import java.util.List;

public interface CustomerService {
    boolean login(String email, String password) throws CouponSystemException;
    Coupon purchaseCoupon(int customerId,int couponId) throws CouponSystemException;
    Customer updateCustomer(Customer customer) throws CouponSystemException;
    Customer getByEmail(String email);
    List<Coupon> getCustomersCoupons(int customerId) throws CouponSystemException;
    List<Coupon> getCouponsByCategory(int customerId,Category category) throws CouponSystemException;
    List<Coupon> getCustomerCouponsByMaxPrice(int customerId,double price) throws CouponSystemException;
    Customer getDetails(int customerId);
}
