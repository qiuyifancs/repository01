package com.itheima.service;

import com.itheima.domain.Order;

import java.util.List;

public interface OrderService {

    /**
     * 查询所有订单信息
     * @return
     */
    List<Order> findOrdersByPage(Integer pageNum,Integer pageSize);

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    Order findOrderById(String id);

}
