package com.miaosha.controller;

import com.miaosha.base.vo.Goods;
import com.miaosha.base.vo.OrderDetailVo;
import com.miaosha.base.vo.OrderInfo;
import com.miaosha.base.vo.User;
import com.miaosha.common.CodeMsg;
import com.miaosha.common.Result;
import com.miaosha.service.GoodsService;
import com.miaosha.service.OrderService;
import com.miaosha.service.RedisService;
import com.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: --------------------------------------
 * @ClassName: OrderController.java
 * @Date: 2021/3/1 23:06
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, User user,
                                      @RequestParam("orderId") long orderId) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        Goods goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
        // TODO LXJ 超卖：数据库唯一索引 + 减库存判断当前库存量>0
    }
