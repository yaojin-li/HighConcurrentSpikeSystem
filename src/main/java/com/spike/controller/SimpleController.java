package com.spike.controller;

import com.spike.base.vo.User;
import com.spike.common.CodeMsg;
import com.spike.common.Result;
import com.spike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/success")
    @ResponseBody
    public Result<String> success(){
        return Result.sucess("success, lixj");
    }

    @RequestMapping("/error")
    @ResponseBody
    public Result<String> error(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name", "lixj");
        return "hello";
    }

    @RequestMapping("/user")
    @ResponseBody
    public Result<User> user(){
        User allUser = userService.selectAll();
        return Result.sucess(allUser);
    }
}
