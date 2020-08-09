package com.spike.common;

/**
 * @Description: --------------------------------------
 * @ClassName: UserTokenPrefix.java
 * @Date: 2020/8/9 15:58
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class UserTokenPrefix extends BasePrefix {
    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    private UserTokenPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserTokenPrefix token = new UserTokenPrefix(TOKEN_EXPIRE, "tk");
}
