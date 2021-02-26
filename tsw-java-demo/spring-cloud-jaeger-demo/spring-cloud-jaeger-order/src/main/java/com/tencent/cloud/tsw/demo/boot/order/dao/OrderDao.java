package com.tencent.cloud.tsw.demo.boot.order.dao;

import com.tencent.cloud.tsw.demo.boot.common.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class OrderDao {
	private static final Logger LOG = LoggerFactory.getLogger(OrderDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int create(Order order) {
		String sql = "INSERT INTO tsw_demo.tws_demo_order (product_id, qty, account_id) VALUES (?, ?, ?)";
		try {
			GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, order.getProductId());
					ps.setInt(2, order.getQty());
					ps.setInt(3, order.getAccountId());
					return ps;
				}
			}, keyHolder);
			return keyHolder.getKey().intValue();
		} catch (Exception e) {
			LOG.error("Create order failed.", e);
			throw new RuntimeException("Create order failed.");
		}
	}

	public boolean finish(Order order) {
		String sql = "UPDATE tsw_demo.tws_demo_order SET status = 1 WHERE status = 0 AND order_id = ?";
		try {
			return jdbcTemplate.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, order.getOrderId());
				}
			}) > 0;
		} catch (Exception e) {
			LOG.error("Finish order failed.", e);
			throw new RuntimeException("Finish order failed.");
		}
	}
}
