package com.yc.bbnmd1.web.controllers;

import com.yc.bbnmd1.entity.Replay;
import com.yc.bbnmd1.entity.Topic;
import com.yc.bbnmd1.future.BbnmdPostFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bbnmd/post")
public class PostController {
    private static Logger logger = LoggerFactory.getLogger(PostController.class.getName());

    @Autowired
    private BbnmdPostFuture bbnmdPostFuture;

    @RequestMapping(value = "/{id}")
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        return bbnmdPostFuture.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CompletableFuture<String> update(@RequestBody Replay replay) {
        return bbnmdPostFuture.update(replay);
    }

    @RequestMapping(value = "/saveTopic/{token}",method = RequestMethod.POST)
    public CompletableFuture<String> saveTopic(@RequestBody Topic topic,@PathVariable(value = "token") String token) {
        return bbnmdPostFuture.createTopic(topic,token);
    }

    @RequestMapping(value = "/saveReplay/{token}",method = RequestMethod.POST)
    public CompletableFuture<String> saveReplay(@RequestBody Replay replay, HttpSession session,@PathVariable(value = "token") String token) {
        System.out.println(replay.toString());
        return bbnmdPostFuture.createReplay(replay,token);
    }

}
