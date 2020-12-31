package com.yc.bbnmd1.future;

import com.yc.bbnmd1.entity.Replay;
import com.yc.bbnmd1.entity.Topic;
import com.yc.bbnmd1.service.PostRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class BbnmdPostFuture {
    @Autowired
    private PostRestService postRestService;

    @Async
    public CompletableFuture<String> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return postRestService.findById(id);
        });
    }


    @Async
    public CompletableFuture<String> createReplay(Replay replay,String token) {
        return CompletableFuture.supplyAsync(() -> {
            return postRestService.createReplay(replay,token);
        });
    }

    @Async
    public CompletableFuture<String> createTopic(Topic topic,String token) {
        return CompletableFuture.supplyAsync(() -> {
            return postRestService.createTopic(topic,token);
        });
    }

    @Async
    public CompletableFuture<String> update(Replay replay) {
        return CompletableFuture.supplyAsync(() -> {
            return postRestService.update(replay);
        });
    }

}

