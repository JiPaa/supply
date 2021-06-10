package com.jconvey.supplier.dao;

import com.jconvey.supplier.bean.File;
import com.jconvey.supplier.bean.User;

import java.util.List;

public interface FileMapper {
    int deleteByPrimaryKey(String username);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);

    List<File> selectAllFile();

    File selectByName(String name);
}