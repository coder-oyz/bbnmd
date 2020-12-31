package com.yc.bbnmd1.web.controllers;

import com.google.gson.Gson;
import com.yc.bbnmd1.future.BbnmdViewFuture;
import com.yc.bbnmd1.redis.RedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("bbnmd/view")
public class ViewController {
    private static Logger logger = LoggerFactory.getLogger(ViewController.class.getName());

    @RequestMapping(value = "/")
    public String getIndex() {
        return "index";
    }

//    String findIndex();
    @Autowired
    private BbnmdViewFuture bbnmdViewFuture;
    @Autowired
    private RedisServiceImpl redisService;
    @RequestMapping(value = "/check/{token}",method = RequestMethod.GET)
    public CompletableFuture<String> check(@PathVariable(value = "token") String token) {
        return bbnmdViewFuture.check(token);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        return bbnmdViewFuture.findById(id);
    }




    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer page, Integer pageSize,Integer bid) {
        return bbnmdViewFuture.findPage(page, pageSize,bid);
    }
    @RequestMapping(value = "/getgood", method = RequestMethod.GET)
    public String getall(String tid) {
        Gson gson=new Gson();


        return gson.toJson(redisService.getValue("IDTID2"+tid));
    }
    //点赞的工具函数
    public Map getreaMap(List<Map> allmap,String rid){
         for(int i=0;i<allmap.size();i++){
                    if(allmap.get(i).get("rid").equals(rid)||allmap.get(i).get(rid)==rid){
                        return allmap.get(i);
                    }
        }
        return null;
    }
    //点赞的工具函数
    public void removeone(List<Map> allmap,String rid){
        for(int i=0;i<allmap.size();i++){
            if(allmap.get(i).get("rid").equals(rid)||allmap.get(i).get(rid)==rid){
                    allmap.remove(allmap.get(i));
                    break;
            }
        }

    }
    @RequestMapping(value = "/good", method = RequestMethod.GET)
    public String good(String uid, String tid,String rid) {
        Map map=new HashMap<String ,String>();
        List listmap=new ArrayList<Map>();
        Gson gson=new Gson();
        if(redisService.getMapValue("IDTID2"+tid)!=null) {
            listmap = (List<Map>) redisService.getMapValue("IDTID2" + tid);
            if (getreaMap(listmap,rid) != null) {
                Map uidmap = getreaMap(listmap,rid);
                System.out.println("取出的"+gson.toJson(uidmap));
                if (uidmap.get(uid) != null) {
                    map.put("code", "0");
                    map.put("data", "您已经点赞过啦");
                } else {
                    uidmap.put(uid, "1");
                    uidmap.put("rid", rid);
                    uidmap.put("total", Integer.parseInt(uidmap.getOrDefault("total", 0).toString()) + 1);
                    listmap.add(uidmap);
                    removeone(listmap,rid);
                    map.put("code", "1");
                    map.put("data", uidmap.get("total"));
                    redisService.setValue("IDTID2" + tid, listmap);
                }
                System.out.println(gson.toJson(listmap));

            } else {
                Map uidmap = new HashMap<String, String>();
                uidmap.put("rid", rid);
                uidmap.put(uid, "1");
                uidmap.put("total", "1");
                listmap.add(uidmap);
                redisService.setValue("IDTID2" + tid, listmap);
                map.put("code", "1");
                map.put("data", 1);
                System.out.println(gson.toJson(listmap));
            }
        }else{
            Map uidmap = new HashMap<String, String>();
            uidmap.put(uid, "1");
            uidmap.put("rid", rid);
            uidmap.put("total", "1");
            listmap.add(uidmap);
            redisService.setValue("IDTID2" + tid, listmap);
            map.put("code", "1");
            map.put("data", 1);
            System.out.println(gson.toJson(listmap));
        }

//        if(redisService.getValue(uid+"*"+rid)==null){
//        redisService.setValue(uid+"*"+rid,1);
//            if(redisService.getValue("reply"+rid)!=null) {
//                redisService.setValue(
//                        "reply" + rid
//                        , Integer.parseInt(redisService.getValue("reply" + rid).toString()) + 1
//                );
//            }else{
//                redisService.setValue(
//                        "reply" + rid
//                        ,   1
//                );
//            }
//            map.put("code","1");
//            map.put("data",redisService.getValue("reply"+rid));
//
//        }else{
//            map.put("code","0");
//            map.put("data","您已经点赞过啦");
//        }

        return gson.toJson(map);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public CompletableFuture<String> update(@PathVariable Integer id) {
        return bbnmdViewFuture.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<String> findIndex() {
        return bbnmdViewFuture.findIndex();
    }
}
