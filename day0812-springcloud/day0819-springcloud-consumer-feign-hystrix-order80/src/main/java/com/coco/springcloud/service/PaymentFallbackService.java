package com.coco.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author cocoLin
 * @create 2021-08-19 17:01
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfoOK(Integer id) {
        return "PaymentFallbackService+****paymentInfoOK+++o(╥﹏╥)o";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "PaymentFallbackService+****paymentInfoTimeOut+++o(╥﹏╥)o";
    }
}
