package com.tencent.cloud.tsw.demo.boot.common.entity;

import java.io.Serializable;

/**
 * 账户
 *
 * @author skyehtzhang
 * @date 2020/11/9
 */
public class Account implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1285909755931952178L;

	private Integer accountId;
	private Integer balance;

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

	/**
	 * @return the balance
	 */
	public Integer getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
}
