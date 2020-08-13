package com.miaosha.service;

import com.miaosha.base.dao.OrderDao;
import com.miaosha.base.dao.OrderInfoDao;
import com.miaosha.base.vo.MiaoshaGoods;
import com.miaosha.base.vo.Order;
import com.miaosha.base.vo.OrderInfo;
import com.miaosha.base.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: --------------------------------------
 * @ClassName: OrderService.java
 * @Date: 2020/8/13 11:57
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderInfoDao orderInfoDao;

    public Order queryOrderByUserIdGoodsId(Long userId, Long goodsId) {
        return orderDao.queryOrderByUserIdGoodsId(userId, goodsId);
    }

    @Transactional
    public OrderInfo createOrder(User user, MiaoshaGoods miaoshaGoods) {
        // 秒杀订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(user.getId());
        orderInfo.setGoodsId(miaoshaGoods.getGoodsId());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsName(miaoshaGoods.getGoodsName());
        orderInfo.setGoodsPrice(miaoshaGoods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStartus(0);
        orderInfoDao.insert(orderInfo);

        //
        Order order = new Order();
        order.setGoodsId(miaoshaGoods.getGoodsId());
        order.setOrderId(orderInfo.getId());
        order.setUserId(user.getId());
        orderDao.insert(order);
        return orderInfo;
    }
}
