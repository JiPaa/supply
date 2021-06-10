package com.jconvey.supplier.dao;

import com.jconvey.supplier.bean.User;

public interface UserMapper {//只有管理员和采购员的username和name有关联，供应商这俩关联应该在FileMapper中
    int deleteByPrimaryKey(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByName(String name);
}