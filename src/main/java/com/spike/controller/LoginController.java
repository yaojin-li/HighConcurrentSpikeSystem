package com.spike.controller;

import com.alibaba.fastjson.JSONObject;
import com.spike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: --------------------------------------
 * @ClassName: LoginController.java
 * @Date: 2020/8/8 16:22
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    public UserService userService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    /**
     * 登录
     * */
    @RequestMapping("/do_login")
    @ResponseBody
    public String doLogin(HttpServletRequest request, HttpServletResponse response){//为什么是response，保证同一个请求？
        Map<String, Object> result = userService.login(request, response);
        return JSONObject.toJSONString(result);
    }



}
