package com.coco.springcloud.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author cocoLin
 * @create 2021-08-15 15:21
 */
@RestController
@Slf4j
public class OrderZkController {

    public  static final String INVOKE_URL="http://springcloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/payment/zk")
    public String get8004(){
        String result=restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
        System.out.println(result);
        return result;
    }

}
