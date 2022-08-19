package com.project3.couponSystem.beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project3.couponSystem.front.ClientType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "customers")
@Data@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "come on we are sure that you have a first name")
    private String firstName;
    @NotBlank(message = "come on we are sure that you have a last name")
    private String last_Name;
    @Email
    private String email;
    private String password;
    private ClientType clientType;
    private String image="https://xsgames.co/randomusers/avatar.php?g=pixel";


    @Singular
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "customers")
    private Set<Coupon> coupons = new HashSet<>();
}
