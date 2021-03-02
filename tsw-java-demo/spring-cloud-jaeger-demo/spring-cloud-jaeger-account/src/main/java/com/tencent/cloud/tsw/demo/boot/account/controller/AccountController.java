package com.tencent.cloud.tsw.demo.boot.account.controller;

import com.tencent.cloud.tsw.demo.boot.account.service.AccountService;
import com.tencent.cloud.tsw.demo.boot.common.entity.Account;
import com.tencent.cloud.tsw.demo.boot.common.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author skyehtzhang
 * @date 2020/11/10
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	private final Random RANDOM = new Random();

	@RequestMapping("/pay")
	public Boolean pay(@RequestBody Order order) {
		// 模拟实际环境
		int rand = RANDOM.nextInt(100);
		if (rand >= 95) {
			// 5% 异常
			throw new RuntimeException("POST spring-boot-account failed.");
		} else if (rand < 10) {
			// 10% 高延迟(1~5s)
			try {
				Thread.sleep(RANDOM.nextInt(4000) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Account account = new Account();
		account.setAccountId(order.getAccountId());
		account.setBalance(order.getQty());
		if (!accountService.pay(account)) {
			return false;
		}
		LOG.info("OrderId [{}] is paid.", order.getOrderId());
		return true;
	}

}
