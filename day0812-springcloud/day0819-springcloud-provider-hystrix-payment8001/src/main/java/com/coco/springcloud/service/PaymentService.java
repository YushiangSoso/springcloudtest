package com.coco.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author cocoLin
 * @create 2021-08-19 14:25
 * 里面包含一个可以正常执行的方法 和一个可能导致出错的方法；
 */
@Service
public class PaymentService {
    public String paymentInfoOK(Integer id){
        return "线程池:"+Thread.currentThread().getName()+"paymentInfoOK,id: "+id+"\t"+"O(∩_∩)O";

    }

    /**
     * 超时访问，演示降级
     * @param id
     * @return
     */

    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public String paymentInfoTimeOut(Integer id)
    {   //int i=10/0;
        try {
            TimeUnit.MILLISECONDS.sleep(3000); } catch
        (InterruptedException e) { e.printStackTrace(); }
        return "线程池:"+Thread.currentThread().getName()+"paymentInfoTimeOut,id: "+id+"\t"+"O(∩_∩)O，耗费3秒";
    }


    public String paymentInfoTimeOutHandler(Integer id){
        return "线程池:"+Thread.currentThread().getName()+"paymentInfoTimeOutHandler,id: "+id+"\t"+"系统繁忙，请稍后再试o(╥﹏╥)o";

    }


    //====以下为服务降级
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//开启熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败多少次后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }



}
