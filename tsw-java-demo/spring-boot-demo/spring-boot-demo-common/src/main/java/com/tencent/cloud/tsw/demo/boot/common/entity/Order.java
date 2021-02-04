package com.tencent.cloud.tsw.demo.boot.common.entity;

import java.io.Serializable;

/**
 * 订单
 *
 * @author skyehtzhang
 * @date 2020/11/6
 */
public class Order implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1529319999880221257L;

	private Integer orderId;
	private Integer productId;
	private Integer qty;
	private Integer accountId;

	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

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

	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

}
