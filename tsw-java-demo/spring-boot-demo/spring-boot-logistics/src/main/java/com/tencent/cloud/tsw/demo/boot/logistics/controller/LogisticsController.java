package com.tencent.cloud.tsw.demo.boot.logistics.controller;

import com.tencent.cloud.tsw.demo.boot.common.entity.Logistics;
import com.tencent.cloud.tsw.demo.boot.logistics.proxy.EmailRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author skyehtzhang
 * @date 2020/11/10
 */
@RestController
@RequestMapping("/logistics")
public class LogisticsController {

	private static final Logger LOG = LoggerFactory.getLogger(LogisticsController.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private EmailRestTemplate emailRestTemplate;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${spring.boot.demo.logistics.topic:tsw}")
	private String topic;

	private final Random RANDOM = new Random();

	@RequestMapping("/create")
	public Boolean create(@RequestBody Logistics logistics) {
		// 模拟实际环境
		int rand = RANDOM.nextInt(100);
		if (rand >= 95) {
			// 5% 异常
			throw new RuntimeException("POST spring-boot-logistics failed.");
		} else if (rand < 10) {
			// 10% 高延迟(1~5s)
			try {
				Thread.sleep(RANDOM.nextInt(4000) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
			ops.set(topic, "TSW is the best!");
//		if (!emailRestTemplate.send(logistics)) {
//			return false;
//		}
			kafkaTemplate.send(topic, logistics.getOrderId() + "");
			LOG.info("Logistics of orderId [{}] is created.", logistics.getOrderId());
			return true;
		} catch (Exception e) {
			LOG.error("Logistics of orderId [{}] is created failed.", logistics.getOrderId(), e);
			return false;
		}
	}

}
