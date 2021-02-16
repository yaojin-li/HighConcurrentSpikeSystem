package com.miaosha.controller;

import com.miaosha.base.vo.MiaoshaGoods;
import com.miaosha.base.vo.User;
import com.miaosha.redis.GoodsKey;
import com.miaosha.service.GoodsService;
import com.miaosha.service.RedisService;
import com.miaosha.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    public RedisService redisService;

    @Autowired
    public UserService userService;

    @Autowired
    public GoodsService goodsService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String toList(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model, User user) {
        // 全局参数配置 UserArgumentResolver
        model.addAttribute("user", user);

        //
        List<MiaoshaGoods> goodsList = goodsService.listGoods();
        model.addAttribute("goodsList", goodsList);
//        return "goods_list";

        // 先从缓存中获取
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        WebContext springWebContext = new WebContext(request, response,
                request.getServletContext(),
                request.getLocale(), model.asMap());
        // 手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", springWebContext);
        if (!StringUtils.isEmpty(html)) {
            // 设置页面缓存 此时Redis中key: GoodsKey:gl
            redisService.set(GoodsKey.getGoodsList, "", html);
        }
        return html;
    }


    @RequestMapping(value = "/to_detail/{goodsId}", produces = "text/html")
    @ResponseBody
    public String detail(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model, @PathVariable("goodsId") String goodsId, User user) {
        model.addAttribute("user", user);

        MiaoshaGoods miaoshaGoods = goodsService.getMiaoshaGoodsByGoodsId(goodsId);
        if (null == miaoshaGoods) {
            logger.error(String.format("商品id[%s]查询商品为空。", goodsId));
            model.addAttribute("errmsg", "查询异常...");
            return "miaosha_fail";
        }
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
//        return "goods_detail";

        // 先从缓存中获取
        String html = redisService.get(GoodsKey.getGoodsDetail, "" + goodsId, String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        //
        WebContext webContext = new WebContext(request, response,
                request.getServletContext(), request.getLocale(), model.asMap());
        // 手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", webContext);
        if (!StringUtils.isEmpty(html)) {
            // 设置页面缓存 此时Redis中key: GoodsKey:gd1 或者 GoodsKey:gd2
            redisService.set(GoodsKey.getGoodsDetail, "" + goodsId, html);
        }
        return html;

    }


}
