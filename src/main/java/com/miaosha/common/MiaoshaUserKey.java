package com.miaosha.common;

/**
 * @Description: --------------------------------------
 * @ClassName: MiaoshaUserKey.java
 * @Date: 2020/8/9 15:58
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class MiaoshaUserKey extends BasePrefix {
    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    private MiaoshaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE, "tk");

    public static MiaoshaUserKey getById = new MiaoshaUserKey(0, "id");

}
