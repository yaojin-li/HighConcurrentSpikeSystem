package com.spike.controller;

import com.spike.base.vo.User;
import com.spike.common.UserTokenPrefix;
import com.spike.service.RedisService;
import com.spike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

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
        return "goods_list";
    }

}
