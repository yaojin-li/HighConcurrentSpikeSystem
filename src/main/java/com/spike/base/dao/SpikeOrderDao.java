package com.spike.base.dao;

import com.spike.base.vo.SpikeOrder;
import com.spike.base.vo.SpikeOrderExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SpikeOrderDao {
    int countByExample(SpikeOrderExample example);

    int deleteByExample(SpikeOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SpikeOrder record);

    int insertSelective(SpikeOrder record);

    List<SpikeOrder> selectByExample(SpikeOrderExample example);

    SpikeOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SpikeOrder record, @Param("example") SpikeOrderExample example);

    int updateByExample(@Param("record") SpikeOrder record, @Param("example") SpikeOrderExample example);

    int updateByPrimaryKeySelective(SpikeOrder record);

    int updateByPrimaryKey(SpikeOrder record);
}