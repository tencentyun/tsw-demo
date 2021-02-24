package com.tencent.cloud.tsw.demo.boot.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author skyehtzhang
 * @date 2020/11/5
 */
@SpringBootApplication
public class LogisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
