package com.yc.bbnmd1.dao.impl;

import com.yc.bbnmd1.dao.MisBaseMapper;
import com.yc.bbnmd1.entity.Replay;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作replay表的mapper
public interface ReplayMapper extends MisBaseMapper<Replay> {
}
