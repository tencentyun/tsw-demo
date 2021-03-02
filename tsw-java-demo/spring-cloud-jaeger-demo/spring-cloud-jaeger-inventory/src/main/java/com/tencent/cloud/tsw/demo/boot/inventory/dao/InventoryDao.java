package com.tencent.cloud.tsw.demo.boot.inventory.dao;

import com.tencent.cloud.tsw.demo.boot.common.entity.Inventory;
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
 * @date 2020/11/9
 */
@Repository
public class InventoryDao {

	private static final Logger LOG = LoggerFactory.getLogger(InventoryDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean deduct(Inventory inventory) {
		String sql = "UPDATE tsw_demo.tsw_demo_inventory SET qty = qty - ? WHERE product_id = ?";
		try {
			return jdbcTemplate.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, inventory.getQty());
					ps.setInt(2, inventory.getProductId());
				}
			}) > 0;
		} catch (Exception e) {
			LOG.error("Create inventory failed.", e);
			throw new RuntimeException("Create inventory failed.");
		}
	}

}
