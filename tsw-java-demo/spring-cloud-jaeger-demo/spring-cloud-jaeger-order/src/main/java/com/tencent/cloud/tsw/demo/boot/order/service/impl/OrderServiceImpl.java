package com.tencent.cloud.tsw.demo.boot.order.service.impl;

import com.tencent.cloud.tsw.demo.boot.order.dao.OrderDao;
import com.tencent.cloud.tsw.demo.boot.common.entity.Order;
import com.tencent.cloud.tsw.demo.boot.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author skyehtzhang
 * @date 2020/11/9
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int create(Order order) {
		return orderDao.create(order);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean finish(Order order) {
		return orderDao.finish(order);
	}
}
