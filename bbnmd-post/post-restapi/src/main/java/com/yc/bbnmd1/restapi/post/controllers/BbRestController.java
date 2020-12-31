package com.yc.bbnmd1.restapi.post.controllers;

import com.google.gson.Gson;
import com.yc.bbnmd1.entity.Replay;
import com.yc.bbnmd1.entity.Topic;
import com.yc.bbnmd1.entity.User;
import com.yc.bbnmd1.redis.RedisServiceImpl;
import com.yc.bbnmd1.service.PostService;
import com.yc.bbnmd1.service.ReplayService;
import com.yc.bbnmd1.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/bbnmd/post")
public class BbRestController {
    private static Logger logger = LoggerFactory.getLogger(BbRestController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private ReplayService replayService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private RedisServiceImpl redisserviceImpl;

    @RequestMapping(value = "/replay/{id}")
    public CompletableFuture<String> findById(@PathVariable Integer id) {

        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            Replay replay = replayService.findOne(id);
            //协议
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", replay);
            System.out.println("map::::"+map);
            return new Gson().toJson(map);
        });

    }


    @RequestMapping(value = "/saveTopic/{token}",method = RequestMethod.POST)
    public CompletableFuture<String> saveTopic(@RequestBody Topic topic, HttpSession session,@PathVariable(value = "token") String token) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            User user= (User)  redisserviceImpl.getValue(token);
            Map<String, Object> map = new HashMap<>();
            if(user==null){
                user=new User();
                user.setUid(1);
            }else {
                topic.setUid(user.getUid());
                topicService.save(topic);
                logger.info("新增->ID=" + topic.getTid());

                map.put("code", 1);
                map.put("data", topic);
            }

            return new Gson().toJson(map);
        });
    }


    @RequestMapping(value = "/saveReplay/{token}",method = RequestMethod.POST)
    public CompletableFuture<String> saveReplay(@RequestBody Replay replay, HttpSession session,@PathVariable(value = "token") String token) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("进入api");
            User user= (User) redisserviceImpl.getValue(token);
            Map<String, Object> map = new HashMap<>();
            if(user==null){
                user=new User();
                user.setUid(1);
            }else{
                replay.setUid(user.getUid());
                replayService.save(replay);
                logger.info("新增->ID=" + replay.getRid());
                map.put("code", 1);
                map.put("data", replay);
            }



            return new Gson().toJson(map);
        });
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CompletableFuture<String> update(@RequestBody Replay replay) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            int num=postService.update(replay);
            //System.out.println("这是返回的值"+num);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", num);
            return new Gson().toJson(map);
        });
    }
}
