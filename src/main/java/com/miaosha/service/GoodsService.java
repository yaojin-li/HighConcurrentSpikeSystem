package com.miaosha.service;

import com.miaosha.base.dao.MiaoshaGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: GoodsService.java
 * @Date: 2020/8/10 13:55
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class GoodsService {

    @Autowired
    private MiaoshaGoodsDao miaoshaGoodsDao;

    public List<MiaoshaGoods> listGoods(){
        return miaoshaGoodsDao.listGoods();
    }

    public MiaoshaGoods getMiaoshaGoodsByGoodsId(Long goodsId){
        return miaoshaGoodsDao.getMiaoshaGoodsByGoodsId(goodsId);
    }

    // 减库存
    public void reduceStock(Long goodsId){
        miaoshaGoodsDao.reduceStock(goodsId);
    }

}
