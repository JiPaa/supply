package com.jconvey.supplier.controller;

import com.jconvey.supplier.bean.User;
import com.jconvey.supplier.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;

@RestController
public class RegisterController {

    @Autowired(required = false)
    UserMapper usermapper;


    @ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
    public String handerler(){
        return "error";
    }

    @RequestMapping("/register")//输入user类，注册
    public  String register(@RequestBody User user){
        System.out.println(user.getUsername());
        int i =usermapper.insertSelective(user);
        if(i>0)
            return "success";
        else
            return "error";
    }
}
