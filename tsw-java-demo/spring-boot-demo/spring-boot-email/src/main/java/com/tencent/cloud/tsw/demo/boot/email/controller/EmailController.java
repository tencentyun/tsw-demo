package com.tencent.cloud.tsw.demo.boot.email.controller;

import com.tencent.cloud.tsw.demo.boot.common.entity.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author skyehtzhang
 * @date 2020/11/10
 */
@RestController
@RequestMapping("/email")
public class EmailController {

	private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	private final Random RANDOM = new Random();

	@RequestMapping("/send")
	public Boolean send(@RequestBody Email email) {
		// 模拟实际环境
		int rand = RANDOM.nextInt(100);
		if (rand >= 95) {
			// 5% 异常
			throw new RuntimeException("POST spring-boot-email failed.");
		} else if (rand < 10) {
			// 10% 高延迟(1~5s)
			try {
				Thread.sleep(RANDOM.nextInt(4000) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		Object value = ops.get("tsw");
		LOG.info("Redis value of key[tsw] is [{}] when orderId is [{}].", value, email.getOrderId());
		LOG.info("Email of orderId [{}] is sent.", email.getOrderId());
		return true;
	}

}
