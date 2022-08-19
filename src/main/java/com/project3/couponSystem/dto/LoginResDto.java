package com.project3.couponSystem.dto;

import com.project3.couponSystem.front.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

/**
 * Created by kobis on 19 May, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class LoginResDto {

    private ClientType clientType;
    private String email;
    private UUID token;


}
