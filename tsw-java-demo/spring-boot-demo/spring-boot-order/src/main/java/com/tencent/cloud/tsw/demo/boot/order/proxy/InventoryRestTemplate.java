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
public class InventoryRestTemplate {

	@Autowired
	private RestTemplate restTemplate;

	public Boolean deduct(Order order) {
		return restTemplate.postForObject("http://127.0.0.1:19102/inventory/deduct", order, Boolean.class);
	}

}
