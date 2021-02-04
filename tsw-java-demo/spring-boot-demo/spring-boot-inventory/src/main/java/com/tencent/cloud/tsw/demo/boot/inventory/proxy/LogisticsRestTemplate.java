package com.tencent.cloud.tsw.demo.boot.inventory.proxy;

import com.tencent.cloud.tsw.demo.boot.common.entity.Logistics;
import com.tencent.cloud.tsw.demo.boot.common.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author skyehtzhang
 * @date 2020/11/10
 */
@Component
public class LogisticsRestTemplate {

	@Autowired
	private RestTemplate restTemplate;

	public Boolean create(Order order) {
		// 发送邮件
		Logistics logistics = new Logistics();
		logistics.setOrderId(order.getOrderId());
		return restTemplate.postForObject("http://127.0.0.1:19103/logistics/create", logistics, Boolean.class);
	}

}
