package com.tencent.cloud.tsw.demo.boot.inventory.service;

import com.tencent.cloud.tsw.demo.boot.common.entity.Inventory;

/**
 * @author skyehtzhang
 * @date 2020/11/10
 */
public interface InventoryService {

	boolean deduct(Inventory inventory);

}
