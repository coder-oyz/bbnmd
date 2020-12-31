package com.yc.bbnmd1.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageDomain<T> {
    private Integer page = 1;   //当前第几页
    private Integer pageSize = 5;  //每页多少条
    private Long total = 0L;  //总记录数
    private Integer totalPages;
    private Integer maxPage;

    private List<T> data;   //当前查询出来的数据集


}
