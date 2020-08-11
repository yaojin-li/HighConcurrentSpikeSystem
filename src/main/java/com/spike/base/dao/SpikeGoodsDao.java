package com.spike.base.dao;

import com.spike.base.vo.SpikeGoods;
import com.spike.base.vo.SpikeGoodsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hibernate.annotations.Parameter;

@Mapper
public interface SpikeGoodsDao {
    int countByExample(SpikeGoodsExample example);

    int deleteByExample(SpikeGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SpikeGoods record);

    int insertSelective(SpikeGoods record);

    List<SpikeGoods> selectByExample(SpikeGoodsExample example);

    SpikeGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SpikeGoods record, @Param("example") SpikeGoodsExample example);

    int updateByExample(@Param("record") SpikeGoods record, @Param("example") SpikeGoodsExample example);

    int updateByPrimaryKeySelective(SpikeGoods record);

    int updateByPrimaryKey(SpikeGoods record);


    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.spike_price from spike_goods mg left join goods g on mg.goods_id = g.id")
    List<SpikeGoods> listGoods();


    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.spike_price from spike_goods mg left join goods g on mg.goods_id = g.id where goods_id = #{goodsId}")
    SpikeGoods getSpikeGoodsByGoodsId(@Param("goodsId") String goodsId);

}