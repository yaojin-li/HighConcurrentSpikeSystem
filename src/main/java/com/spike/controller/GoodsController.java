package com.spike.controller;

import com.spike.base.vo.Goods;
import com.spike.base.vo.SpikeGoods;
import com.spike.base.vo.User;
import com.spike.common.UserTokenPrefix;
import com.spike.service.GoodsService;
import com.spike.service.RedisService;
import com.spike.service.UserService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
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
    public String toList(Model model,
                         @CookieValue(value = UserService.TOKEN_NAME_IN_COOKIE, required = false) String cookieToken,
                         @RequestParam(value = UserService.TOKEN_NAME_IN_COOKIE, required = false) String paramToken) {
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return "login";
        }

        // 从cookie或request中获取token
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;

        // 通过token从redis中获取user
        User user = userService.getUserByTokenFromRedis(token);
        model.addAttribute("user", user);

        //
        List<SpikeGoods> goodsList = goodsService.listGoods();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }


    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, @PathVariable("goodsId") String goodsId) {
//        model.addAttribute("user", user);

        SpikeGoods spikeGoods = goodsService.getSpikeGoodsByGoodsId(goodsId);
        model.addAttribute("goods", spikeGoods);

        long startTime = spikeGoods.getStartDate().getTime();
        long endTime = spikeGoods.getEndDate().getTime();
        long nowTime = System.currentTimeMillis();

        // 秒杀状态
        int miaoshaStatus = 0;
        // 倒计时
        int remainSeconds = 0;

        //活动未开始
        if (nowTime < startTime) {
            remainSeconds = (int) (nowTime - startTime) / 1000;
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
