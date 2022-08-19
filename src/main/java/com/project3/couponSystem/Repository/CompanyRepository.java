package com.project3.couponSystem.Repository;

import com.project3.couponSystem.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository <Company,Integer> {

    boolean existsByName(String name);
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email,String password);
    Company findByEmail(String email);
}
