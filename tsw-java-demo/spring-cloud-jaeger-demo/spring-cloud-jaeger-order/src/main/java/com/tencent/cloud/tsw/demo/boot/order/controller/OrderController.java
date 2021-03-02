package com.tencent.cloud.tsw.demo.boot.order.controller;

import com.tencent.cloud.tsw.demo.boot.order.proxy.AccountRestTemplate;
import com.tencent.cloud.tsw.demo.boot.order.proxy.InventoryRestTemplate;
import com.tencent.cloud.tsw.demo.boot.common.entity.Order;
import com.tencent.cloud.tsw.demo.boot.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author skyehtzhang
 * @date 2020/11/9
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private InventoryRestTemplate inventoryRestTemplate;

	@Autowired
	private AccountRestTemplate accountRestTemplate;

	private final Random RANDOM = new Random();

	@RequestMapping("/create")
	@Transactional(rollbackFor = Exception.class)
	public String create(@RequestBody Order order) {
		// 模拟实际环境
		int rand = RANDOM.nextInt(100);
		if (rand >= 95) {
			// 5% 异常
			throw new RuntimeException("POST order failed.");
		} else if (rand < 10) {
			// 10% 高延迟(1~5s)
			try {
				Thread.sleep(RANDOM.nextInt(4000) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 下单
		int orderId = orderService.create(order);
		if (orderId > 0) {
			LOG.info("Order of orderId [{}] is created.", orderId);
			order.setOrderId(orderId);
			// 扣钱
			if (!accountRestTemplate.pay(order)) {
				return "Order of orderId [" + orderId +"] is paid failed.";
			}
			// 减库存
			if (!inventoryRestTemplate.deduct(order)) {
				return "Inventory of orderId [" + orderId +"] is deducted failed.";
			}
			if (!orderService.finish(order)) {
				return "Order of orderId [" + orderId +"] is finished failed.";
			}
			LOG.info("Order of orderId [{}] is finished.", orderId);
			return "Order of orderId [" + orderId +"] is finished.";
		} else {
			return "Order of orderId [" + orderId +"] is created failed.";
		}
	}

}
