package com.coco.springcloud.service.impl;

import com.coco.springcloud.dao.PaymentDao;
import com.coco.springcloud.domain.Payment;
import com.coco.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cocoLin
 * @create 2021-08-12 23:55
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;
    @Override
    public int add(Payment payment) {
       return paymentDao.add(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {

        return paymentDao.getPaymentById(id);
    }
}
