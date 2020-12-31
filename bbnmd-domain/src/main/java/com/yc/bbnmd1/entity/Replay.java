package com.yc.bbnmd1.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data      //  lombok 注解，节省getter, setter
@Table(name = "replay")
public class Replay implements Serializable {
    @Id  // JPA注解,指定此属性为表中的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;
    private String title;
    private String content;
    private String publishtime;
    private String modifytime;
    private Integer tid;
    private Integer uid;

    public Replay(){

    }

    public Replay(String title,String content,Integer tid,Integer uid){
        this.title=title;
        this.content=content;
        this.tid=tid;
        this.uid=uid;
    }

}