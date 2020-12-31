package com.yc.bbnmd1.dao.impl;

import com.yc.bbnmd1.dao.MisBaseMapper;
import com.yc.bbnmd1.entity.Board;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作topic表的mapper
public interface BoardMapper extends MisBaseMapper<Board> {

}
