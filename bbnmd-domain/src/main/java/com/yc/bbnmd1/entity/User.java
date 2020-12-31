package com.yc.bbnmd1.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data      //  lombok 注解，节省getter, setter
@Table(name = "user")
public class User implements Serializable {
    @Id  // JPA注解,指定此属性为表中的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;   //注意: 对应的数据表中的字段名叫 book_id
    private String uname;
    private String upwd;
    private String role;
    private String head;
    private String regtime;
    private Integer gender;
    public User() {
    }



    public User(String uname,String upwd,String head,Integer gender){
        this.uname=uname;
        this.upwd=upwd;
        this.head=head;
        this.gender=gender;
        this.role="普通用户";
    }



}
