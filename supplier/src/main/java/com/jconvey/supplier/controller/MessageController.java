package com.jconvey.supplier.controller;

import com.alibaba.fastjson.JSON;
import com.jconvey.supplier.bean.Fomal;
import com.jconvey.supplier.bean.Message;
import com.jconvey.supplier.bean.User;
import com.jconvey.supplier.dao.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MessageController {

    @Autowired(required = false)
    MessageMapper messageMapper;

    @RequestMapping("/message")//输入user类，返回其信息list
    public  List<Message> message(@RequestBody User user){
        List<Message> list=messageMapper.selectByUsername(user.getUsername());
        return list;
    }

    @RequestMapping("/messagedelete")//输入信息内容，删除该信息数据
    int messagedelete(@RequestBody Message o){
        System.out.println(o.getMessage());
        messageMapper.deleteByMessage(o.getMessage());
        return 0;
    }
}
