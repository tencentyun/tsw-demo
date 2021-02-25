package com.tencent.cloud.tsw.demo.boot.order.proxy;

import com.tencent.cloud.tsw.demo.boot.common.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${spring.boot.demo.account.url:127.0.0.1:19101}")
	private String accountUrl;

	public Boolean pay(Order order) {
		// 扣钱
		return restTemplate.postForObject("http://" + accountUrl + "/account/pay", order, Boolean.class);
	}

}
