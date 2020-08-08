package com.spike.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.thymeleaf.util.StringUtils;

/**
 * @Description: --------------------------------------
 * @ClassName: MD5Util.java
 * @Date: 2020/8/8 11:14
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class MD5Util {

    private static final String DEFAULT_SALT = "1a2b3c4d";

    private static String md5(String input) {
        return DigestUtils.md5Hex(input);
    }


    /**
     * inputPass - DBPass
     * 输入密码加密成DB密码
     */
    public static String inputPassToDBPass(String inputPass, String salt) {
        String formPass = inputPassToFormPass(inputPass);
        return formPassToDBPass(formPass, salt);
    }


    /**
     * inputPass - formPass
     * 输入密码加密成表单密码
     */
    public static String inputPassToFormPass(String inputPass) {
        // 加密策略
        return md5("" + DEFAULT_SALT.charAt(0) + inputPass + DEFAULT_SALT.charAt(DEFAULT_SALT.length() / 2));
    }


    /**
     * formPass - DBPass
     * 表单密码加密成DB密码
     */
    public static String formPassToDBPass(String formPass, String salt) {
        // salt 为空取默认
        if (StringUtils.isEmpty(salt)) {
            salt = DEFAULT_SALT;
        }
        // 加密策略
        return md5("" + salt.charAt(0) + formPass + salt.charAt(salt.length() - 1));
    }


    public static void main(String[] args) {
        String a = "123456";
        System.out.println(inputPassToFormPass(a));
        System.out.println(formPassToDBPass(inputPassToFormPass(a), ""));
    }
}
