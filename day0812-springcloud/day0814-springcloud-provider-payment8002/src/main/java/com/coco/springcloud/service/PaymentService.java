package com.coco.springcloud.service;

import com.coco.springcloud.domain.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author cocoLin
 * @create 2021-08-12 23:55
 */
public interface PaymentService {
    public int add(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
