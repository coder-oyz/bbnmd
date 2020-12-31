package com.yc.bbnmd1.entity;

import lombok.Data;

@Data
public class View {
    private Integer topicid;
    private String title;
    private String content;
    private String publishtime;
    private String modifytime;
    private Integer uid;
    private Integer boardid;
    private String boardname;
    private String parentid;
    private String userid;
    private String uname;
    private String upass;
    private String head;
    private String regtime;
    private String gender;
    private Long total;
}
