package com.project3.couponSystem.services;

import com.project3.couponSystem.beans.Company;
import com.project3.couponSystem.beans.Customer;
import com.project3.couponSystem.exceptions.CouponSystemException;

import java.util.List;

public interface AdminService {
    boolean login(String email, String password) throws CouponSystemException;

    Company addCompany(Company company) throws CouponSystemException;

    Company updateCompany(Company company) throws CouponSystemException;

    void deleteCompany(int company_id) throws CouponSystemException;

    List<Company> getAllCompanies();

    Company getSingleCompany(int company_id) throws CouponSystemException;

    Customer addCustomer(Customer customer) throws CouponSystemException;
    Customer updateCustomer(Customer customer) throws CouponSystemException;
    void deleteCustomer(int id) throws CouponSystemException;
    List<Customer> getCustomers();
    Customer getCustomer(int id) throws CouponSystemException;
}
