package com.tencent.cloud.tsw.demo.boot.common.entity;

import java.io.Serializable;

/**
 * 库存
 *
 * @author skyehtzhang
 * @date 2020/11/9
 */
public class Inventory implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -813982978778555574L;

	private Integer productId;
	private Integer qty;

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the qty
	 */
	public Integer getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(Integer qty) {
		this.qty = qty;
	}
}
