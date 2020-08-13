package com.miaosha.service;

import com.miaosha.base.dao.MiaoshaGoodsDao;
import com.miaosha.base.dao.OrderInfoDao;
import com.miaosha.base.vo.MiaoshaGoods;
import com.miaosha.base.vo.OrderInfo;
import com.miaosha.base.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Description: --------------------------------------
 * @ClassName: MiaoshaService.java
 * @Date: 2020/8/13 11:54
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class MiaoshaService {
    private static final Logger logger = LoggerFactory.getLogger(MiaoshaService.class);

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo doMiaosha(User user, MiaoshaGoods miaoshaGoods) {
        //减库存
        goodsService.reduceStock(miaoshaGoods.getGoodsId());

        //下订单 写入秒杀订单 order_info maiosha_order
        return orderService.createOrder(user, miaoshaGoods);
    }

}
