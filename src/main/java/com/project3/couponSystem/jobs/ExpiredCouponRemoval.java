package com.project3.couponSystem.jobs;

import com.project3.couponSystem.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpiredCouponRemoval {
    private static final int DAY = 1000 * 60 * 60 * 24;
    private final CouponRepository couponRepository;

    @Scheduled(fixedDelay = DAY)
    public void print() throws Exception {
        couponRepository.deleteExpired();
    }
}
