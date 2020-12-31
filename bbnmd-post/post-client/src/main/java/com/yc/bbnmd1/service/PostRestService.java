package com.yc.bbnmd1.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.bbnmd1.client.PostClient;
import com.yc.bbnmd1.entity.Replay;
import com.yc.bbnmd1.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//Hystrix服务层:  调用 PiclibClient,实现断路器功能
@Service
public class PostRestService {
    @Autowired(required = false)
    private PostClient postClient;

//    String update(@RequestBody Replay replay);

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public String findById(Integer id) {
        return postClient.findById(id);
    }

    private String findByIdFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }


    @HystrixCommand(fallbackMethod = "createFallback1")
    public String createReplay(Replay repaly,String token) {
        return postClient.createReplay(repaly,token);
    }
    private String createFallback1(Replay repaly,String token) {
        System.out.println(repaly.toString());
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加" + repaly.getRid());
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "createFallback2")
    public String createTopic(Topic topic,String token) {
        return postClient.createTopic(topic, token);
    }

    private String createFallback2(Topic topic,String token) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加" + topic.getTid());
        return new Gson().toJson(map);
    }


    @HystrixCommand(fallbackMethod = "updateFallback")
    public String update(Replay replay) {
        return postClient.update(replay);
    }

    private String updateFallback(Replay replay) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法修改" + replay.getRid());
        return new Gson().toJson(map);
    }


}


