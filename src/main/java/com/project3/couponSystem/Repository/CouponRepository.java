package com.project3.couponSystem.Repository;

import com.project3.couponSystem.beans.Category;
import com.project3.couponSystem.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsByCompany_idAndTitle(int companyId, String title);

    List<Coupon> findByCompany_id(int company_id);

    List<Coupon> findByCategoryAndCompany_id(Category category, int company_id);

    List<Coupon> findByCompany_idAndPriceLessThan(int company_id, double price);
    @Transactional
    @Modifying
    @Query(value = "UPDATE `couponSystem3`.`coupons` SET `amount` =amount -1 WHERE (id = ?);", nativeQuery = true)
    void buyCoupon(@Param("id") int id);

    @Query(value = "SELECT amount  FROM couponSystem3.coupons where id=?", nativeQuery = true)
    int isCouponLeft(@Param("id") int id);
    @Query(value = "select exists(select * FROM couponSystem3.coupons_customers where customers_id=? and coupons_id=? ) as res;",nativeQuery = true)
    int isBought(@Param("coupons_id") int couponId,@Param("customers_id")int customerId);
    @Query(value = "delete FROM couponSystem3.coupons where end_date<now()",nativeQuery = true)
    @Transactional
    @Modifying
    void deleteExpired();
    @Query(value = "delete FROM couponsystem3.coupons where company_id = ?",nativeQuery = true)
    @Transactional
    @Modifying
    void deleteCompanyCoupons(@Param("company_id") int company_id);
    @Query(value = "SELECT coupons_id FROM couponSystem3.coupons_customers where customers_id= ?",nativeQuery = true)
    List<Integer> getCouponsIds(@Param("id") int id);
    boolean existsByIdAndCategory(int id,Category category);
    boolean existsByIdAndPriceLessThan(int id,double price);
    @Query(value = "SELECT * FROM couponsystem3.coupons where amount>0 and end_date>=now();",nativeQuery = true)
    List<Coupon> getAllCoupons();
}
