package com.spike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/to_list")
    public String toList(){
        return "goods_list";
    }

}
