package com.miaosha.base.dao;

import java.util.List;

import com.miaosha.base.vo.MiaoshaOrder;
import com.miaosha.base.vo.MiaoshaOrderExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MiaoshaOrderDao {
    int countByExample(MiaoshaOrderExample example);

    int deleteByExample(MiaoshaOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MiaoshaOrder record);

    int insertSelective(MiaoshaOrder record);

    List<MiaoshaOrder> selectByExample(MiaoshaOrderExample example);

    MiaoshaOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MiaoshaOrder record, @Param("example") MiaoshaOrderExample example);

    int updateByExample(@Param("record") MiaoshaOrder record, @Param("example") MiaoshaOrderExample example);

    int updateByPrimaryKeySelective(MiaoshaOrder record);

    int updateByPrimaryKey(MiaoshaOrder record);

    MiaoshaOrder queryOrderByUserIdGoodsId(@Param("userId") Long userId,@Param("goodsId") Long goodsId);
}