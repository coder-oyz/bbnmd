package com.yc.bbnmd1.domain;


import lombok.Data;


@Data
public class ViewDomain extends PageDomain {

    private Integer tid;
    private String title;
    private String content;
    private String publishtime;
    private String modifytime;
    private Integer uid;
    private Integer bid;
    private String bname;
    private String parentid;
    private String userid;
    private String uname;
    private String upwd;
    private String head;
    private String regtime;
    private String gender;
}
