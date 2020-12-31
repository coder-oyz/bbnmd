package com.yc.bbnmd1.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.bbnmd1.client.ViewClient;
import com.yc.bbnmd1.domain.ViewDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
//import com.yc.bbnmd1.domain.ViewDomain;
import java.util.HashMap;
import java.util.Map;

//Hystrix服务层:  调用 PiclibClient,实现断路器功能
@Service
public class ViewRestService {
    @Autowired(required = false)
    private ViewClient viewClient;


    @HystrixCommand(fallbackMethod = "checkFallback")
    public String check(String token) {
        return viewClient.check(token);
    }

    private String checkFallback(String token) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("111msg", "服务异常");
        return new Gson().toJson(map);
    }


    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public String findById(Integer id) {

        return viewClient.findById(id);
    }

    private String findByIdFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }



    @HystrixCommand(fallbackMethod = "findAllFallback")
    public String findAll(Integer page, Integer pageSize,Integer bid) {
        return viewClient.findAll(page, pageSize,bid);
    }

    private String findAllFallback(Integer page, Integer pageSize,Integer bid) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("dvdvdvdmsg", "服务异常");
        return new Gson().toJson(map);
    }


    @HystrixCommand(fallbackMethod = "findIndexFallback")
    public String findIndex() {
        return viewClient.findIndex();
    }

    private String findIndexFallback() {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }


    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return viewClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }


}


