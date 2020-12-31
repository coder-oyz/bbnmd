package com.yc.bbnmd1.dao.impl;

import com.yc.bbnmd1.dao.MisBaseMapper;
import com.yc.bbnmd1.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作user表的mapper
public interface UserMapper extends MisBaseMapper<User> {
}


