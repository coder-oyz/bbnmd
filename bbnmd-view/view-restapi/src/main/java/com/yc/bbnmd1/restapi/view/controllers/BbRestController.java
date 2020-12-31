package com.yc.bbnmd1.restapi.view.controllers;


import com.google.gson.Gson;

import com.netflix.discovery.shared.Application;
import com.yc.bbnmd1.domain.PageDomain;
import com.yc.bbnmd1.domain.ReplayVO;
import com.yc.bbnmd1.domain.ViewDomain;
import com.yc.bbnmd1.entity.Board;
import com.yc.bbnmd1.entity.Topic;
import com.yc.bbnmd1.entity.User;
import com.yc.bbnmd1.redis.RedisServiceImpl;
import com.yc.bbnmd1.service.ReplayService;
import com.yc.bbnmd1.service.ViewService;
import com.yc.bbnmd1.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("bbnmd/view")
public class BbRestController {
    private static Logger logger = LoggerFactory.getLogger(BbRestController.class);

    @Autowired
    private ViewService viewService;

    @Autowired
    private RedisServiceImpl redisserviceImpl;

    @RequestMapping(value = "/check/{token}")
    public CompletableFuture<String> doCheck(HttpSession session,@PathVariable(value = "token") String token) {
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {

            System.out.println(token);
            User user=(User)   redisserviceImpl.getValue(token);
//            System.out.println(   redisserviceImpl.getValue(token));
            Map<String, Object> map = new HashMap<>();
            if(user==null){
                System.out.println("用户未登录");
                map.put("code", 0);
                map.put("data", "用户未登录");
            }else {
                //协议

                map.put("code", 1);
                map.put("data", user);
            }
            System.out.println("map::::"+map);
            return new Gson().toJson(map);
        });
    }

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<String> findIndex() {
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            List<Board> parent=new ArrayList<Board>();
            List<Board> child=new ArrayList<Board>();
            List<Board> allBoards=viewService.getBoardAndTopic();
            Map<String, Object> map=new HashMap<String, Object>();
            for(int i=0;i<allBoards.size();i++) {
                Board board=allBoards.get(i);
                if(board.getParentid()==0) {
                    parent.add(board);//顶级板块信息存在parent
                }else {
                    child.add(board);
                }
            }
            map.put("parent",parent);
            map.put("child", child);
            map.put("code", 1);

            System.out.println("map::::"+map);
            return new Gson().toJson(map);
        });
    }





    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CompletableFuture<String> findById(@PathVariable Integer id) {

        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            ViewDomain vo=new ViewDomain();
            vo.setTid(id);
            List<ViewDomain> list01=viewService.getTopic(vo);
            ReplayVO v=new ReplayVO();
            if(null!=vo) {
                v.setTid(vo.getTid());
            }
            List<ReplayVO> list02=viewService.finds(v);

            Map<String, Object> map=new HashMap<String, Object>();
            if(null!=list01&&!list01.isEmpty()) {
                map.put("topic", list01.get(0));
            }
            map.put("replays", list02);
            map.put("code", 1);
            System.out.println("topic+repaly+map::::"+map);
            return new Gson().toJson(map);
        });

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("viewAPI:delete id");
            viewService.deleteReplay(id);
            logger.info("删除->ID=" + id);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            return new Gson().toJson(map);
        });
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer page, Integer pageSize,Integer bid) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("page:"+page+"pageSize:"+pageSize+"bid"+bid);
            try {
                ViewDomain viewDomain = new ViewDomain();
                viewDomain.setBid(bid);

                if (CommonUtils.isNotNull(page)) {
                    viewDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    viewDomain.setPageSize(pageSize);
                }

                PageDomain<ViewDomain> pageDomain = viewService.getTopicByPage(viewDomain);

                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                map.put("data", pageDomain);


                return new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

}
