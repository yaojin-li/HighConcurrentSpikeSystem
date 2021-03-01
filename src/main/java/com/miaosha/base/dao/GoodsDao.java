package com.miaosha.base.dao;

import java.util.List;

import com.miaosha.base.vo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsDao {

    int insert(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Goods record);

}