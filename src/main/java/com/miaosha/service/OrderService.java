package com.miaosha.service;

import com.miaosha.base.dao.MiaoshaOrderDao;
import com.miaosha.base.dao.OrderInfoDao;
import com.miaosha.base.vo.MiaoshaGoods;
import com.miaosha.base.vo.MiaoshaOrder;
import com.miaosha.base.vo.OrderInfo;
import com.miaosha.base.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    MiaoshaOrderDao miaoshaOrderDao;

    @Autowired
    OrderInfoDao orderInfoDao;

    public MiaoshaOrder queryOrderByUserIdGoodsId(Long userId, Long goodsId) {
        return miaoshaOrderDao.queryOrderByUserIdGoodsId(userId, goodsId);
    }

    @Transactional
    public OrderInfo createOrder(User user, MiaoshaGoods miaoshaGoods) {
        // 秒杀订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(user.getId());
        orderInfo.setGoodsId(miaoshaGoods.getId());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsName(miaoshaGoods.getGoodsName());
        orderInfo.setGoodsPrice(miaoshaGoods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setCreateDate(new Date());
        orderInfoDao.insert(orderInfo);

        //
        MiaoshaOrder order = new MiaoshaOrder();
        order.setGoodsId(miaoshaGoods.getId());
        order.setOrderId(orderInfo.getId());
        order.setUserId(user.getId());
        miaoshaOrderDao.insert(order);
        return orderInfo;
    }
}
