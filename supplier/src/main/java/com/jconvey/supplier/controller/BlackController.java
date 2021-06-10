package com.jconvey.supplier.controller;

import com.jconvey.supplier.bean.*;
import com.jconvey.supplier.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BlackController {

    @Autowired(required = false)
    BlackMapper blackMapper;

    @Autowired(required = false)
    MessageMapper messageMapper;

    @Autowired(required = false)
    FileMapper fileMapper;

    @Autowired(required = false)
    BlackprocessMapper blackprocessMapper;

    @Autowired(required = false)
    UserMapper userMapper;

    @ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
    public String handerler(){return "error";}

    @RequestMapping("/getblack")//获取所有黑名单
    public List<Black> getblack(){
        return blackMapper.selectAllBlack();
    }

    @RequestMapping("/nametousername")//将Blackprocess类中的供应商姓名转化为username，返回username
    public String nametousername(@RequestBody Blackprocess a){
        File file= fileMapper.selectByName(a.getSupply());
        if(file==null){
            return "error";
        }
        else
            return file.getUsername();
    }

    @RequestMapping("/insertblackprocess")//插入blackprocess表项
    public String insertblackprocess(@RequestBody Blackprocess a){
        a.setState("1");
        blackprocessMapper.insertSelective(a);
        return "success";
    }

    @RequestMapping("/getblackprocess")//获取blackprocess表项并转化为辅助类blackassistant返回
    public List<Blackassistant> getblackprocess(){
        List<Blackprocess> process=blackprocessMapper.selectAll();
        List<Blackassistant> res=new ArrayList<>();
        Blackassistant ass=new Blackassistant();
        for(int i=0;i<process.size();i++){
            ass.setMarket(process.get(i).getMarket());
            ass.setReason(process.get(i).getReason());
            ass.setName(fileMapper.selectByPrimaryKey(process.get(i).getSupply()).getName());
            res.add(ass);
        }
        return res;
    }

    @RequestMapping("/passblack")//审核通过黑名单申请，传来的类类型为blackassistant
    public String passblack(@RequestBody Blackassistant a){
        String username=fileMapper.selectByName(a.getName()).getUsername();
        blackprocessMapper.deleteByPrimaryKey(username);
        Black black=blackMapper.selectByPrimaryKey(username);
        if(black==null){
            Black b=new Black();
            b.setName(a.getName());
            b.setMarket(a.getMarket());
            b.setManage(a.getManage());
            b.setUsername(fileMapper.selectByName(a.getName()).getUsername());
            blackMapper.insert(b);
        }
        else{
            blackMapper.deleteByPrimaryKey(black.getUsername());
        }
        Message m=new Message();
        String s="您对"+a.getName()+"的黑名单管理申请已经通过。";
        m.setTo(userMapper.selectByName(a.getMarket()).getUsername());
        m.setMessage(s);
        messageMapper.insertSelective(m);
        return "0";
    }

    @RequestMapping("/refuseblack")//审核拒绝黑名单申请，传来的类类型为blackassistant
    public String refuseblack(@RequestBody Blackassistant a){
        String username=fileMapper.selectByName(a.getName()).getUsername();
        blackprocessMapper.deleteByPrimaryKey(username);
        Message m=new Message();
        String s="您对"+a.getName()+"的黑名单管理申请已被拒绝。";
        m.setTo(userMapper.selectByName(a.getMarket()).getUsername());
        m.setMessage(s);
        messageMapper.insertSelective(m);
        return "0";
    }
}
