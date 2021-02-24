package com.tencent.cloud.tsw.demo.boot.inventory.service.impl;

import com.tencent.cloud.tsw.demo.boot.common.entity.Inventory;
import com.tencent.cloud.tsw.demo.boot.inventory.dao.InventoryDao;
import com.tencent.cloud.tsw.demo.boot.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author skyehtzhang
 * @date 2020/11/10
 */
@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryDao inventoryDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean deduct(Inventory inventory) {
		return inventoryDao.deduct(inventory);
	}
}
