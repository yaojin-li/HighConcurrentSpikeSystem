package com.spike.common;

/**
 * @Description: --------------------------------------
 * @ClassName: BasePrefix.java
 * @Date: 2020/8/7 21:26
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public abstract class BasePrefix implements KeyPrefix {

    // 过期时间
    private int expireSeconds;

    // 前缀
    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
        // 默认0代表永不过期
        this(0, prefix);
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        // 以当前类名拼接
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
