package com.jconvey.supplier.controller;

import com.alibaba.fastjson.JSON;
import com.jconvey.supplier.bean.File;
import com.jconvey.supplier.bean.Fomal;
import com.jconvey.supplier.bean.Message;
import com.jconvey.supplier.bean.User;
import com.jconvey.supplier.dao.FileMapper;
import com.jconvey.supplier.dao.FomalMapper;
import com.jconvey.supplier.dao.MessageMapper;
import com.jconvey.supplier.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ReadController {

    @Autowired(required = false)
    FomalMapper fomalMapper;

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    MessageMapper messageMapper;

    @Autowired(required = false)
    FileMapper fileMapper;

    @RequestMapping("/readall2")//获取所有需采购部门审批的档案
    public  List<Fomal> readall2(){
        List<Fomal> fomals = fomalMapper.selectByState("1");
        return fomals;
    }

    @RequestMapping("/readall1")//获取所有需管理部门审批的档案
    public  List<Fomal> readall1(){
        List<Fomal> fomals = fomalMapper.selectByState("2");
        return fomals;
    }

    @RequestMapping("/pass2")//在采购部门审批通过后更新
    public  String pass2(@RequestBody Fomal a){
        a.setState("2");
        fomalMapper.updateByPrimaryKeySelective(a);
        return "0";
    }

    @RequestMapping("/pass1")//在采购部门审批通过后更新
    public  String pass1(@RequestBody Fomal a){
        fomalMapper.deleteByPrimaryKey(a.getUsername());
        File f=fileMapper.selectByPrimaryKey(a.getUsername());
        if(f==null){
            f=new File();
            f.setBrand(a.getBrand());
            f.setClassify(a.getClassify());
            f.setEmail(a.getEmail());
            f.setMoney(a.getMoney());
            f.setName(a.getName());
            f.setPerson(a.getPerson());
            f.setProduction(a.getProduction());
            f.setRange(a.getRange());
            f.setTel(a.getTel());
            f.setUsername(a.getUsername());
            fileMapper.insert(f);
            User us=new User();
            us.setState("4");
            us.setUsername(f.getUsername());
            userMapper.updateByPrimaryKeySelective(us);
        }
        else{
            f.setBrand(a.getBrand());
            f.setClassify(a.getClassify());
            f.setEmail(a.getEmail());
            f.setMoney(a.getMoney());
            f.setName(a.getName());
            f.setPerson(a.getPerson());
            f.setProduction(a.getProduction());
            f.setRange(a.getRange());
            f.setTel(a.getTel());
            f.setUsername(a.getUsername());
            fileMapper.updateByPrimaryKey(f);
        }
        String s=a.getUsername()+"您好！您修改信息/正式注册的申请已经成功。采购部门的意见为：\""+a.getMarketadvice()+"\"管理部门的意见为：\""+a.getManageadvice()+"\"";
        Message m=new Message();
        m.setMessage(s);
        m.setTo(a.getUsername());
        messageMapper.insertSelective(m);
        return "0";
    }

    @RequestMapping("/refuse2")//在采购部门审批通过后更新
    public  String refuse2(@RequestBody Fomal a){
        fomalMapper.deleteByPrimaryKey(a.getUsername());
        Message m=new Message();
        m.setTo(a.getUsername());
        String s=a.getUsername()+"您好！您正式注册申请已被驳回，理由是\""+a.getMarketadvice()+"\"。请您修改信息后再次申请。";
        m.setMessage(s);
        messageMapper.insertSelective(m);
        return "0";
    }

    @RequestMapping("/refuse1")//在采购部门审批通过后更新
    public  String refuse1(@RequestBody Fomal a){
        fomalMapper.deleteByPrimaryKey(a.getUsername());
        Message m=new Message();
        m.setTo(a.getUsername());
        String s=a.getUsername()+"您好！您正式注册申请已被驳回，理由是\""+a.getMarketadvice()+"\"。请您修改信息后再次申请。";
        m.setMessage(s);
        messageMapper.insertSelective(m);
        return "0";
    }
}
