package com.project3.couponSystem.Repository;

import com.project3.couponSystem.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email,String password);
    Customer findByEmail(String email);

}
