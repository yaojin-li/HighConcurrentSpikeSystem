package com.miaosha.base.vo;

/**
 * @Description: --------------------------------------
 * @ClassName: GoodsDetailVo.java
 * @Date: 2021/2/16 17:51
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class GoodsDetailVo {

    // 秒杀状态
    private int miaoshaStatus = 0;
    // 倒计时
    private int remainSeconds = 0;

    private MiaoshaGoods goods;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public MiaoshaGoods getGoods() {
        return goods;
    }

    public void setGoods(MiaoshaGoods goods) {
        this.goods = goods;
    }
}
