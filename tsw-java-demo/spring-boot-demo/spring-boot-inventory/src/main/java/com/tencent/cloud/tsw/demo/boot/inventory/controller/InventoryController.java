package com.tencent.cloud.tsw.demo.boot.inventory.controller;

import com.tencent.cloud.tsw.demo.boot.inventory.proxy.LogisticsRestTemplate;
import com.tencent.cloud.tsw.demo.boot.common.entity.Inventory;
import com.tencent.cloud.tsw.demo.boot.common.entity.Order;
import com.tencent.cloud.tsw.demo.boot.inventory.service.InventoryService;
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
@RequestMapping("/inventory")
public class InventoryController {

	private static final Logger LOG = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private LogisticsRestTemplate logisticsRestTemplate;

	private final Random RANDOM = new Random();

	@RequestMapping("/deduct")
	public Boolean deduct(@RequestBody Order order) {
		// 模拟实际环境
		int rand = RANDOM.nextInt(100);
		if (rand >= 95) {
			// 5% 异常
			throw new RuntimeException("POST spring-boot-inventory failed.");
		} else if (rand < 10) {
			// 10% 高延迟(1~5s)
			try {
				Thread.sleep(RANDOM.nextInt(4000) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		try {
			Inventory inventory = new Inventory();
			inventory.setProductId(order.getProductId());
			inventory.setQty(order.getQty());
			if (!logisticsRestTemplate.create(order)) {
				return false;
			}
			if (!inventoryService.deduct(inventory)) {
				return false;
			}
			LOG.info("Inventory of orderId [{}] is deducted.", order.getOrderId());
			return true;
		} catch (Exception e) {
			LOG.error("Inventory of orderId [{}] is deducted failed.", order.getOrderId(), e);
			return false;
		}

	}
}
