package com.jconvey.supplier.dao;

import com.jconvey.supplier.bean.Message;

import java.util.List;

public interface MessageMapper {
    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByUsername(String username);

    int deleteByMessage(String message);
}