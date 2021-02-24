package com.tencent.cloud.tsw.demo.boot.logistics.proxy;

import com.tencent.cloud.tsw.demo.boot.common.entity.Email;
import com.tencent.cloud.tsw.demo.boot.common.entity.Logistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author skyehtzhang
 * @date 2020/11/10
 */
@Component
public class EmailRestTemplate {

	@Autowired
	private RestTemplate restTemplate;

	public Boolean send(Logistics logistics) {
		// 发送邮件
		Email email = new Email();
		email.setOrderId(logistics.getOrderId());
		return restTemplate.postForObject("http://127.0.0.1:19114/email/send", email, Boolean.class);
	}

}
