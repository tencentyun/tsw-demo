package com.tencent.cloud.tsw.demo.boot.order.service;

import com.tencent.cloud.tsw.demo.boot.common.entity.Order;

/**
 * 订单服务
 *
 * @author skyehtzhang
 * @date 2020/11/9
 */
public interface OrderService {

    int create(Order order);

    boolean finish(Order order);

}
