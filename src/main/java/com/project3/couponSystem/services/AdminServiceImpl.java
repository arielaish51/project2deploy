package com.project3.couponSystem.services;

import com.project3.couponSystem.beans.Company;
import com.project3.couponSystem.beans.Customer;
import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.exceptions.ErrMsg;
import com.project3.couponSystem.front.ClientType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl extends ClientService implements AdminService {
    private final CompanyService companyService;
    @Override
    public boolean login(String email, String password)  {
        if (!(email.equals("admin@admin.com") && password.equals("admin"))){
            return false;
        }
        return true;
    }

    @Override
    public Company addCompany(Company company) throws CouponSystemException {
        if (this.companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrMsg.NAME_ALREADY_EXIST);
        }
        if (this.companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXIST);
        }
        company.setClientType(ClientType.Company);
        companyRepository.save(company);
        return company;
    }

    @Override
    public Company updateCompany(Company company) throws CouponSystemException {
        if (!this.companyRepository.existsById(company.getId())) {
            throw new CouponSystemException(ErrMsg.DontExistException);
        }
        Company company1 = companyRepository.getById(company.getId());
        if (!company1.getName().equals(company.getName())){
            throw new CouponSystemException(ErrMsg.CANT_CHANGE_NAME);
        }
        if (company1.getId()!=company.getId()){
            throw  new CouponSystemException(ErrMsg.CANT_CHANGE_COMPANY_ID);
        }
        companyRepository.saveAndFlush(company);
    return company;
    }

    @Override
    public void deleteCompany(int company_id) throws CouponSystemException {
        if (!this.companyRepository.existsById(company_id)) {
            throw new CouponSystemException(ErrMsg.DontExistException);
        }

        couponRepository.deleteCompanyCoupons(company_id);
        companyRepository.deleteById(company_id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int company_id) throws CouponSystemException {
        return companyRepository.findById(company_id).orElseThrow(() ->new CouponSystemException(ErrMsg.DontExistException));
    }

    @Override
    public Customer addCustomer(Customer customer) throws CouponSystemException {
        if(customerRepository.existsByEmail(customer.getEmail())){
            throw  new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXIST);
        }
        customer.setClientType(ClientType.Customer);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CouponSystemException {
        if (!customerRepository.existsById(customer.getId())){
            throw new CouponSystemException(ErrMsg.DontExistException);
        }
        customerRepository.saveAndFlush(customer);
        return customer;
    }

    @Override
    public void deleteCustomer(int id) throws CouponSystemException {
        if (!customerRepository.existsById(id)){
            throw new CouponSystemException(ErrMsg.DontExistException);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(int id) throws CouponSystemException {
      return customerRepository.findById(id).orElseThrow(()->new CouponSystemException(ErrMsg.DontExistException));
    }


}
