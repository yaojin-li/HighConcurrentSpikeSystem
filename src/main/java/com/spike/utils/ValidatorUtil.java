package com.spike.utils;

import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: --------------------------------------
 * @ClassName: ValidatorUtil.java
 * @Date: 2020/8/8 19:17
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class ValidatorUtil {

    // 校验手机号的正则
    private static final Pattern mobilePattern = Pattern.compile("1//d{10}");

    public static boolean isMobile(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return false;
        }else {
            // 校验是否匹配
            Matcher matcher = mobilePattern.matcher(mobile);
            return matcher.matches();
        }

    }

}
