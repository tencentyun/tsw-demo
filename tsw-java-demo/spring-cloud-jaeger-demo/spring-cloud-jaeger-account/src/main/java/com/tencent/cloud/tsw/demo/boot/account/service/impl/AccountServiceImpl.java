package com.tencent.cloud.tsw.demo.boot.account.service.impl;

import com.tencent.cloud.tsw.demo.boot.account.dao.AccountDao;
import com.tencent.cloud.tsw.demo.boot.account.service.AccountService;
import com.tencent.cloud.tsw.demo.boot.common.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author skyehtzhang
 * @date 2020/11/10
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean pay(Account account) {
		return accountDao.pay(account);
	}
}
