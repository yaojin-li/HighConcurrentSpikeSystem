package com.spike.common;

/**
 * @Description: --------------------------------------
 * @ClassName: KeyPrefix.java
 * @Date: 2020/8/7 21:25
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();

}
