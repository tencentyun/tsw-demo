package com.tencent.cloud.tsw.demo.boot.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author skyehtzhang
 * @date 2020/11/5
 */
@SpringBootApplication
@EnableTransactionManagement
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
