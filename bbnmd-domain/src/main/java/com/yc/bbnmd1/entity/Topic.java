package com.yc.bbnmd1.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;

@Data      //  lombok 注解，节省getter, setter
@Table(name = "topic")
public class Topic implements Serializable {
    @Id  // JPA注解,指定此属性为表中的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;   //注意: 对应的数据表中的字段名叫 book_id
    private String title;
    private String content;
    private String publishtime;
    private String modifytime;
    private Integer uid;
    private Integer hot;
    private Integer bid;

    public Topic() {
        this.hot=0;
    }

    public Topic(String content, Integer uid, String time, Integer hot) {
        this.content = content;
        this.publishtime = time;
        this.uid = uid;
        this.hot = hot;
    }
    public Topic(String title,String content, Integer uid, Integer bid) {
        this.title = title;
        this.content = content;
        this.uid = uid;
        this.bid = bid;
        this.hot=0;
    }


}
