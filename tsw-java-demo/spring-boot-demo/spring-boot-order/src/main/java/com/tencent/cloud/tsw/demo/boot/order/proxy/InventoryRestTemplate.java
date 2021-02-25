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
public class InventoryRestTemplate {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${spring.boot.demo.inventory.url:127.0.0.1:19102}")
	private String inventoryUrl;

	public Boolean deduct(Order order) {
		return restTemplate.postForObject("http://" + inventoryUrl+ "/inventory/deduct", order, Boolean.class);
	}

}
