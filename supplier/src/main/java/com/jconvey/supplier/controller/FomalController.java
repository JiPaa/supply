package com.jconvey.supplier.controller;

import com.alibaba.fastjson.JSON;
import com.jconvey.supplier.bean.File;
import com.jconvey.supplier.bean.Fomal;
import com.jconvey.supplier.dao.FomalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class FomalController {

    @Autowired(required = false)
    FomalMapper fomalMapper;

    @ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
    public String handerler(){
        return "error";
    }

    @RequestMapping("/fomal")//档案修改或提交
    public  String fomal(@RequestBody Fomal f){
        f.setState("1");
        fomalMapper.insertSelective(f);
        return "success";
    }
}