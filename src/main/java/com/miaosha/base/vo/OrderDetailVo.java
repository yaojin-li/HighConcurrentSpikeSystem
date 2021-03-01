package com.miaosha.base.vo;

/**
 * @Description: --------------------------------------
 * @ClassName: OrderDetailVo.java
 * @Date: 2021/3/1 23:16
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/

public class OrderDetailVo {
    private Goods goods;
    private OrderInfo order;
    public Goods getGoods() {
        return goods;
    }
    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    public OrderInfo getOrder() {
        return order;
    }
    public void setOrder(OrderInfo order) {
        this.order = order;
    }
}
