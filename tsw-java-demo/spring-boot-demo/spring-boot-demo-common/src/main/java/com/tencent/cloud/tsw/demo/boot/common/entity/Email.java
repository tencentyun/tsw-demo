package com.tencent.cloud.tsw.demo.boot.common.entity;

import java.io.Serializable;

/**
 * @author skyehtzhang
 * @date 2020/11/10
 */
public class Email implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2661108418778053477L;

	private Integer orderId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}
