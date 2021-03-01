package com.miaosha.controller;

import com.miaosha.common.CodeMsg;
import com.miaosha.common.Result;
import com.miaosha.redis.UserKey;
import com.miaosha.service.RedisService;
import com.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: SimpleController.java
 * @Date: 2020/6/6 22:12
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@RequestMapping("/demo")
public class SimpleController {

    @Autowired
    public UserService userService;

    @Autowired
    public RedisService redisService;

    @RequestMapping("/success")
    @ResponseBody
    public Result<String> success() {
        return Result.sucess("success, lixj");
    }

    @RequestMapping("/error")
    @ResponseBody
    public Result<String> error() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "lixj");
        return "hello";
    }

    @RequestMapping("/user")
    @ResponseBody
    public Result<List<User>> user() {
        List<User> allUser = userService.selectAll();
        return Result.sucess(allUser);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<String> redisGet() {
        String str = redisService.get(UserKey.getById,"0", String.class);
        return Result.sucess(str);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<String> redisSet() {
        User user = new User();
        user.setId(Long.valueOf(1));
        user.setNickname("test_demo");
        if (redisService.set(UserKey.getById,"0", user)){
            return Result.sucess("true");
        }
        return Result.sucess("false");
    }


}
