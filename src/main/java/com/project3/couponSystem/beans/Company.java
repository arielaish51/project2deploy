package com.project3.couponSystem.beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project3.couponSystem.front.ClientType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company extends Users{
    @Id
    @GeneratedValue
    private int id;
    @NotBlank(message = "come on we are sure that you have a name")
    private String name;
    private String password;
    @Email(message = "please insert a correct email")
    private String email;

    private ClientType clientType;
    @Singular
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Coupon> coupons = new ArrayList<>();

}
