package com.spike.controller;

import com.spike.common.CodeMsg;
import com.spike.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: --------------------------------------
 * @ClassName: SimpleController.java
 * @Date: 2020/6/6 22:12
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
@RequestMapping("/demo")
public class SimpleController {

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
}
