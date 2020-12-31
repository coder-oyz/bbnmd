package com.yc.bbnmd1.service;

import com.yc.bbnmd1.dao.impl.ReplayMapper;
import com.yc.bbnmd1.entity.Replay;
import com.yc.bbnmd1.entity.Topic;
import com.yc.bbnmd1.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class PostServiceImpl implements PostService{
    @Autowired(required = false)
    private ReplayMapper replayMapper;


    @Override
    public void postTopic(Topic topic) {

    }

    @Override
    public void postReplay(Replay replay) {

    }

    @Override
    public int update(Replay replay) {
        replay.setModifytime(CommonUtils.formatDateTime(new Date()));
        return replayMapper.updateByPrimaryKeySelective(replay);
    }

}
