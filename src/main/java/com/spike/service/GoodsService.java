package com.spike.service;

import com.spike.base.dao.GoodsDao;
import com.spike.base.dao.SpikeGoodsDao;
import com.spike.base.dao.UserDao;
import com.spike.base.vo.Goods;
import com.spike.base.vo.SpikeGoods;
import com.spike.base.vo.User;
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
    private SpikeGoodsDao spikeGoodsDao;

    public List<SpikeGoods> listGoods(){
        return spikeGoodsDao.listGoods();
    }

    public SpikeGoods getSpikeGoodsByGoodsId(String goodsId){
        return spikeGoodsDao.getSpikeGoodsByGoodsId(goodsId);
    }

}
