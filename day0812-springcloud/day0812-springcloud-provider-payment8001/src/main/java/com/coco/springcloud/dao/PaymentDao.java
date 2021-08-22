package com.coco.springcloud.dao;

import com.coco.springcloud.domain.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author cocoLin
 * @create 2021-08-12 23:35
 */
@Mapper
public interface PaymentDao {
    public int add(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
