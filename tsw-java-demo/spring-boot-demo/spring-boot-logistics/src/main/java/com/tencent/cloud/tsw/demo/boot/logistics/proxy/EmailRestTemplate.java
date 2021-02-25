package com.tencent.cloud.tsw.demo.boot.logistics.proxy;

import com.tencent.cloud.tsw.demo.boot.common.entity.Email;
import com.tencent.cloud.tsw.demo.boot.common.entity.Logistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${spring.boot.demo.email.url:127.0.0.1:19104}")
	private String emailUrl;

	public Boolean send(Logistics logistics) {
		// 发送邮件
		Email email = new Email();
		email.setOrderId(logistics.getOrderId());
		return restTemplate.postForObject("http://" + emailUrl + "/email/send", email, Boolean.class);
	}

}
