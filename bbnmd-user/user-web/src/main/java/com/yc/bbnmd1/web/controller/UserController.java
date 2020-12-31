package com.yc.bbnmd1.web.controller;

import com.yc.bbnmd1.entity.User;
import com.yc.bbnmd1.future.BbnmdUserFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bbnmd/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class.getName());

    @Autowired
    private BbnmdUserFuture bbnmdUserFuture;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        return bbnmdUserFuture.findById(id);
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public CompletableFuture<String> login(String uname,String upwd) {
        return bbnmdUserFuture.login(uname,upwd);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody User user) {
        return bbnmdUserFuture.create(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Integer id) {
        return bbnmdUserFuture.delete(id);
    }

}