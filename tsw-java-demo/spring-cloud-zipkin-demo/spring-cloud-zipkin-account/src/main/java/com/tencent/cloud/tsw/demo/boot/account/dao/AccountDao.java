package com.tencent.cloud.tsw.demo.boot.account.dao;

import com.tencent.cloud.tsw.demo.boot.common.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author skyehtzhang
 * @date 2020/11/10
 */
@Repository
public class AccountDao {

	private static final Logger LOG = LoggerFactory.getLogger(AccountDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean pay(Account account) {
		String sql = "UPDATE tsw_demo.tsw_demo_account SET balance = balance - ? WHERE account_id = ?";
		try {
			return jdbcTemplate.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, account.getBalance());
					ps.setInt(2, account.getAccountId());
				}
			}) > 0;
		} catch (Exception e) {
			LOG.error("Pay failed.", e);
			throw new RuntimeException("Pay failed.");
		}
	}

}
