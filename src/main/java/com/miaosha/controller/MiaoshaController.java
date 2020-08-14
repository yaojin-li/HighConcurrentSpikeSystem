package com.miaosha.controller;

import com.miaosha.base.vo.*;
import com.miaosha.common.CodeMsg;
import com.miaosha.service.GoodsService;
import com.miaosha.service.MiaoshaService;
import com.miaosha.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/do_miaosha")
    public String miaosha(Model model, User user, HttpServletRequest request) {
        model.addAttribute("user", user);

        String goodsId = request.getParameter("goodsId");

        // 查库存
        MiaoshaGoods miaoshaGoods = goodsService.getMiaoshaGoodsByGoodsId(goodsId);
        if (miaoshaGoods.getStockCount() <= 0) {
            logger.error(String.format("用户[%s]秒杀失败，原因：%s", user.getMobile(), CodeMsg.MIAO_SHA_OVER.getMsg()));
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }

        // 查秒杀订单 miaosha_order
        MiaoshaOrder order = orderService.queryOrderByUserIdGoodsId(user.getId(), Long.valueOf(goodsId));
        if (null != order) {
            logger.error(String.format("用户[%s]秒杀失败，原因：%s", user.getMobile(), CodeMsg.REPEATE_MIAOSHA.getMsg()));
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        // 新增秒杀
        OrderInfo orderInfo = miaoshaService.doMiaosha(user, miaoshaGoods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", miaoshaGoods);
        return "order_detail";
    }

}
