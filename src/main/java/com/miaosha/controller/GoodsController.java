package com.miaosha.controller;

import com.miaosha.base.vo.MiaoshaGoods;
import com.miaosha.base.vo.User;
import com.miaosha.service.GoodsService;
import com.miaosha.service.RedisService;
import com.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: GoodsController.java
 * @Date: 2020/8/8 19:04
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    public RedisService redisService;

    @Autowired
    public UserService userService;

    @Autowired
    public GoodsService goodsService;

    @RequestMapping("/to_list")
    public String toList(Model model,User user){
        // 全局参数配置 UserArgumentResolver
        model.addAttribute("user", user);

        //
        List<MiaoshaGoods> goodsList = goodsService.listGoods();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }


    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, @PathVariable("goodsId") String goodsId, User user) {
        model.addAttribute("user", user);

        MiaoshaGoods miaoshaGoods = goodsService.getMiaoshaGoodsByGoodsId(goodsId);
        model.addAttribute("goods", miaoshaGoods);

        long startTime = miaoshaGoods.getStartDate().getTime();
        long endTime = miaoshaGoods.getEndDate().getTime();
        long nowTime = System.currentTimeMillis();

        // 秒杀状态
        int miaoshaStatus = 0;
        // 倒计时
        int remainSeconds = 0;

        //活动未开始
        if (nowTime < startTime) {
            remainSeconds = (int) (startTime - nowTime) / 1000;
        } else if (nowTime > endTime) {
            // 活动已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else { // 活动进行中
            miaoshaStatus = 1;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }


}
