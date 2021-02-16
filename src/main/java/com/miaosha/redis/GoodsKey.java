package com.miaosha.redis;

import com.miaosha.common.BasePrefix;

/**
 * @Description: --------------------------------------
 * @ClassName: GoodsKey.java
 * @Date: 2021/2/16 15:37
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class GoodsKey extends BasePrefix {

    public GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public GoodsKey(String prefix) {
        super(prefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey(60,"gl");

    public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");

}
