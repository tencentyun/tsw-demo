package com.tencent.cloud.tsw.demo.boot.common.entity;

import java.io.Serializable;

/**
 * 物流
 *
 * @author skyehtzhang
 * @date 2020/11/10
 */
public class Logistics implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4611131517484131122L;

	private Integer orderId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}
