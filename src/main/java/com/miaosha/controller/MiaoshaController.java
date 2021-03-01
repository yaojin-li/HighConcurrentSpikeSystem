package com.miaosha.controller;

import com.miaosha.base.vo.*;
import com.miaosha.common.CodeMsg;
import com.miaosha.common.Result;
import com.miaosha.service.GoodsService;
import com.miaosha.service.MiaoshaService;
import com.miaosha.service.OrderService;
import org.aspectj.apache.bcel.classfile.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: --------------------------------------
 * @ClassName: MiaoshaController.java
 * @Date: 2020/8/13 11:52
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    private static final Logger logger = LoggerFactory.getLogger(MiaoshaController.class);

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping(value = "/do_miaosha", method = RequestMethod.POST)
    public Result<OrderInfo> miaosha(Model model, User user, HttpServletRequest request, @RequestParam("goodsId") long goodsId)  {
        model.addAttribute("user", user);
        if (ObjectUtils.isEmpty(user)){
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        // 判断库存
        MiaoshaGoods miaoshaGoods = goodsService.getMiaoshaGoodsByGoodsId(goodsId);
        int stock = miaoshaGoods.getStockCount();
        if (stock <= 0) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }

        // 查秒杀订单 miaosha_order 判断是否已经秒杀到了
        MiaoshaOrder order = orderService.queryOrderByUserIdGoodsId(user.getId(), goodsId);
        if (ObjectUtils.isEmpty(order)) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }

        // 减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.doMiaosha(user, miaoshaGoods);
        return Result.sucess(orderInfo);
    }

}
