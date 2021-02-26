package com.tencent.cloud.tsw.demo.boot.order.proxy;

import com.tencent.cloud.tsw.demo.boot.common.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author skyehtzhang
 * @date 2020/11/9
 */
@Component
public class AccountRestTemplate {

	@Autowired
	private RestTemplate restTemplate;

	public Boolean pay(Order order) {
		// 扣钱
		return restTemplate.postForObject("http://spring-cloud-jaeger-account/account/pay", order, Boolean.class);
	}

}
