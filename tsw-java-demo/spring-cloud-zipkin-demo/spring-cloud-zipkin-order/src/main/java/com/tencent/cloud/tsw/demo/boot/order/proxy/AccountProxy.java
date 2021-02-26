package com.tencent.cloud.tsw.demo.boot.order.proxy;

import com.tencent.cloud.tsw.demo.boot.common.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("spring-cloud-zipkin-account")
public interface AccountProxy {

    @RequestMapping("/account/pay")
    Boolean pay(@RequestBody Order order);
}
