package com.yc.bbnmd1.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bbnmd")
public class indexcroller {
    @RequestMapping("/index.html")
    public String indexhtml(){
        return  "index";
    }

    @RequestMapping("/detail.html")
    public String indexhtml3(){
        return  "detail";
    }
    @RequestMapping("/list.html")
    public String listhtml(){
        return  "list";
    }

    @RequestMapping("/login.html")
    public String loginhtml(){
        return  "login";
    }

    @RequestMapping("/reg.html")
    public String reghtml(){
        return  "reg";
    }

    @RequestMapping("/error.html")
    public String errorhtml(){
        return  "error";
    }
}
