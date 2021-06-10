package com.jconvey.supplier.dao;

import com.jconvey.supplier.bean.Blackprocess;

import java.util.List;

public interface BlackprocessMapper {
    int deleteByPrimaryKey(String supply);

    int insert(Blackprocess record);

    int insertSelective(Blackprocess record);

    Blackprocess selectByPrimaryKey(String supply);

    List<Blackprocess> selectAll();

    int updateByPrimaryKeySelective(Blackprocess record);

    int updateByPrimaryKey(Blackprocess record);
}