package com.project3.couponSystem.services;

import com.project3.couponSystem.beans.Category;
import com.project3.couponSystem.beans.Coupon;
import com.project3.couponSystem.beans.Customer;
import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.exceptions.ErrMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ClientService implements CustomerService {

    private final CompanyService companyService;


    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!customerRepository.existsByEmailAndPassword(email, password))
            return false;

        return true;
    }


    public Customer getByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Coupon purchaseCoupon(int customerId, int couponId) throws CouponSystemException {
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.DontExistException);
        }
        if (couponRepository.isCouponLeft(couponId) == 0) {
            throw new CouponSystemException(ErrMsg.COUPON_OUT_OF_STOCK);
        }
        if (couponRepository.isBought(customerId, couponId) == 1) {
            throw new CouponSystemException(ErrMsg.ONLY_ONE_PURCHASE);
        }
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(()-> new CouponSystemException(ErrMsg.DontExistException));
        if (coupon.getEndDate().before(java.sql.Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrMsg.COUPON_EXPIRED);
        }
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CouponSystemException(ErrMsg.DontExistException));
        customer.getCoupons().add(coupon);
        updateCustomer(customer);
        couponRepository.buyCoupon(couponId);
        coupon.getCustomers().add(customer);
        coupon.setAmount(coupon.getAmount()-1);
        companyService.updateCoupon(coupon.getCompany().getId(),coupon.getId(), coupon);
        return coupon;
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CouponSystemException {
        if (!customerRepository.existsById(customer.getId())) {
            throw new CouponSystemException(ErrMsg.DontExistException);
        }
        return customerRepository.saveAndFlush(customer);
    }


    @Override
    public List<Coupon> getCustomersCoupons(int customerId) throws CouponSystemException {
        Set<?> couponSet = customerRepository.getById(customerId).getCoupons();
        List<Coupon> coupons = customerRepository.getById(customerId).getCoupons().stream().collect(Collectors.toList());
        return coupons;
    }

    @Override
    public List<Coupon> getCouponsByCategory(int customerId, Category category) throws CouponSystemException {
        List<Integer> ids = couponRepository.getCouponsIds(customerId);
        List<Coupon> coupons = new ArrayList<>();
        for (Integer id : ids) {
            if (couponRepository.existsByIdAndCategory(id, category))
                coupons.add(couponRepository.getById(id));
        }
        return coupons;
    }

    @Override
    public List<Coupon> getCustomerCouponsByMaxPrice(int customerId, double price) throws CouponSystemException {
        List<Integer> ids = couponRepository.getCouponsIds(customerId);
        List<Coupon> coupons = new ArrayList<>();
        for (Integer id : ids) {
            if (couponRepository.existsByIdAndPriceLessThan(id, price))
                coupons.add(couponRepository.getById(id));
        }
        return coupons;
    }

    @Override
    public Customer getDetails(int customerId) {
        return customerRepository.getById(customerId);
    }


}
