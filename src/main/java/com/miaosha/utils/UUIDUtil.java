package com.miaosha.utils;

import java.util.UUID;

/**
 * @Description: --------------------------------------
 * @ClassName: UUIDUtil.java
 * @Date: 2020/8/9 15:52
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class UUIDUtil {

    public static String UUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
