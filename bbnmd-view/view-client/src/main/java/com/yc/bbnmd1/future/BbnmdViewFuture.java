package com.yc.bbnmd1.future;

import com.yc.bbnmd1.service.ViewRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class BbnmdViewFuture {
    @Autowired
    private ViewRestService viewRestService;


    @Async
    public CompletableFuture<String> check(String token) {
        return CompletableFuture.supplyAsync(() -> {
            return viewRestService.check(token);
        });
    }


    @Async
    public CompletableFuture<String> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return viewRestService.findById(id);
        });
    }


    @Async
    public CompletableFuture<String> findPage(Integer page, Integer pageSize,Integer bid) {
        return CompletableFuture.supplyAsync(() -> {
            return viewRestService.findAll(page, pageSize,bid);
        });
    }

    @Async
    public CompletableFuture<String> findIndex() {
        return CompletableFuture.supplyAsync(() -> {
            return viewRestService.findIndex();
        });
    }

    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return viewRestService.delete(id);
        });
    }

}

