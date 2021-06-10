package com.jconvey.supplier.dao;

import com.jconvey.supplier.bean.Fomal;

import java.util.List;

public interface FomalMapper {
    int deleteByPrimaryKey(String username);

    int insert(Fomal record);

    int insertSelective(Fomal record);

    Fomal selectByPrimaryKey(String username);

    List<Fomal> selectByState(String state);

    int updateByPrimaryKeySelective(Fomal record);

    int updateByPrimaryKey(Fomal record);
}