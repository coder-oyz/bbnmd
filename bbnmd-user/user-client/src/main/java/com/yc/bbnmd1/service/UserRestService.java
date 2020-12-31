package com.yc.bbnmd1.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.bbnmd1.client.UserClient;
import com.yc.bbnmd1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//Hystrix服务层:  调用 PiclibClient,实现断路器功能
@Service
public class UserRestService {
    @Autowired(required = false)
    private UserClient userClient;


    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public String findById(Integer id) {

        return userClient.findById(id);
    }

    private String findByIdFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "loginFallback")
    public String login(String uname,String upwd) {
        return userClient.login(uname,upwd);
    }

    private String loginFallback(String uname,String upwd) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加用户" + uname);
        return new Gson().toJson(map);
    }





    @HystrixCommand(fallbackMethod = "createFallback")
    public String create(User user) {
        System.out.println("ssssaaaaa");
        System.out.println("ssssaaaaa");
        System.out.println("ssssaaaaa");
        System.out.println("ssssaaaaa");
        return userClient.create(user);
    }

    private String createFallback(User user) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加用户" + user);
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return userClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }


}

