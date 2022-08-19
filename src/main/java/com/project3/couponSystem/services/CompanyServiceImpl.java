package com.project3.couponSystem.services;

import com.project3.couponSystem.beans.Category;
import com.project3.couponSystem.beans.Company;
import com.project3.couponSystem.beans.Coupon;
import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.exceptions.ErrMsg;
import com.project3.couponSystem.exceptions.SecMsg;
import org.springframework.stereotype.Service;

import java.util.List;



@Service

public class CompanyServiceImpl extends ClientService implements CompanyService {



    @Override
    public Company GetCompanyByEmail(String email) {
        return companyRepository.findByEmail(email);
    }


    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (companyRepository.existsByEmailAndPassword(email, password)) {
//            this.companyId = companyRepository.findByEmail(email).getId();
            return true;
        }
        return false;
    }

    @Override
    public Coupon addCoupon(int companyId,Coupon coupon) throws CouponSystemException {
        if (couponRepository.existsByCompany_idAndTitle(companyId, coupon.getTitle()))
            throw new CouponSystemException(ErrMsg.COUPON_ALREADY_EXIST);

        coupon.setCompany(getDetails(companyId));
        couponRepository.save(coupon);
        return coupon;
    }

    @Override
    public Coupon updateCoupon(int companyId,int couponId,Coupon coupon) throws CouponSystemException {
        System.out.println(coupon);
        coupon.setCompany(companyRepository.findById(companyId).orElseThrow(()->new CouponSystemException(ErrMsg.DontExistException)));
        if(coupon.getCompany().getId()!=companyId){
            throw new SecurityException(SecMsg.INVALID_TOKEN.getMsg());
        }
        if (!couponRepository.existsById(companyId))
            throw new CouponSystemException(ErrMsg.DontExistException);
        Coupon coupon1=couponRepository.findById(couponId).get();
        if (coupon1.getCompany().getId() != coupon.getCompany().getId()) {
            throw new CouponSystemException(ErrMsg.CANT_CHANGE_COMPANY_ID);
        }
        couponRepository.saveAndFlush(coupon);
        return coupon;
    }

    @Override
    public void deleteCoupon(int companyId,int id) throws CouponSystemException {

        if (!couponRepository.existsById(id))
            throw new CouponSystemException(ErrMsg.DontExistException);
        couponRepository.deleteById(id);
    }

    @Override
    public List<Coupon> getCoupons(int companyId) {
        return couponRepository.findByCompany_id(companyId);
    }

    @Override
    public List<Coupon> getCouponsByCategory(int companyId,Category category) {
        return couponRepository.findByCategoryAndCompany_id(category, companyId);
    }

    @Override
    public List<Coupon> getCouponsByMaxPrice(int companyId,double price) {
        return couponRepository.findByCompany_idAndPriceLessThan(companyId, price);
    }

    @Override
    public Company getDetails(int companyId) {
        return companyRepository.findById(companyId).get();
    }
}
