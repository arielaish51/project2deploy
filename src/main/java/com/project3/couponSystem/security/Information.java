package com.project3.couponSystem.security;

import com.project3.couponSystem.front.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    private int userId;
    private ClientType clientType;
    private LocalDateTime time;
    private String email;
}
