package com.jconvey.supplier.controller;

import com.alibaba.fastjson.JSON;
import com.jconvey.supplier.bean.File;
import com.jconvey.supplier.bean.User;
import com.jconvey.supplier.dao.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class FileController {

    @Autowired(required = false)
    FileMapper fileMapper;

    @RequestMapping("/file")//输入用户类，根据username查到相应档案
    public  String file(@RequestBody User user){
        File file=fileMapper.selectByPrimaryKey(user.getUsername());
        HashMap<String,Object> res=new HashMap<>();
        res.put("file",file);
        String res_json= JSON.toJSONString(res);
        return res_json;
    }

    @RequestMapping("/allfile")//获取所有档案
    public  List<File> allfile(){
        List<File> files=fileMapper.selectAllFile();
        return files;
    }
}
