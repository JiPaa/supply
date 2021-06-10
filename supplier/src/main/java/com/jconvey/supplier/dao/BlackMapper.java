package com.jconvey.supplier.dao;

import com.jconvey.supplier.bean.Black;

import java.util.List;

public interface BlackMapper {
    int deleteByPrimaryKey(String username);

    int insert(Black record);

    int insertSelective(Black record);

    Black selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(Black record);

    int updateByPrimaryKey(Black record);

    List<Black> selectAllBlack();
}