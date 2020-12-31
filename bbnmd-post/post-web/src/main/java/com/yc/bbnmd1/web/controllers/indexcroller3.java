package com.yc.bbnmd1.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bbnmd")
public class indexcroller3 {
    @RequestMapping("/post.html")
    public String indexhtml(){
        return  "post";
    }


}
