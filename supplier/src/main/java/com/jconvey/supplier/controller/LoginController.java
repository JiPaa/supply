package com.jconvey.supplier.controller;

import com.jconvey.supplier.bean.User;
import com.jconvey.supplier.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired(required = false)
    UserMapper userMapper;

    @RequestMapping("/login")//输入用户类，返回{flag,user}
    public  String login(@RequestBody User user){
        String flag="error";
        User us=userMapper.selectByPrimaryKey(user.getUsername());
        HashMap<String,Object> res=new HashMap<>();
        if(us!=null&&us.getPassword().equals(user.getPassword())){
            flag="ok";
        }
        res.put("flag",flag);
        res.put("user",us);
        String res_json=JSON.toJSONString(res);
        return res_json;
    }

    @RequestMapping("/getuser")//输入可能有错的用户类，根据username返回正确的
    public  User getuser(@RequestBody User user){
        User us=userMapper.selectByPrimaryKey(user.getUsername());
        return us;
    }
}
