package com.yc.bbnmd1.service;

import com.yc.bbnmd1.dao.impl.ReplayMapper;

import com.yc.bbnmd1.entity.Replay;

import com.yc.bbnmd1.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ReplayServiceImpl implements ReplayService {

    @Autowired(required = false)
    private ReplayMapper replayMapper;

    @Override
    public void save(Replay replay) {
        //String title,String content,Integer tid,Integer uid
        Replay repalyBean = new Replay(replay.getTitle(),replay.getContent(),replay.getTid(),replay.getUid());
        repalyBean.setPublishtime(CommonUtils.formatDateTime(new Date()));
        repalyBean.setModifytime(CommonUtils.formatDateTime(new Date()));
        this.replayMapper.insert(repalyBean);

        replay.setRid(repalyBean.getRid());
    }

    @Override
    public void delete(Integer id) {
        this.replayMapper.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Replay findOne(Integer id) {

        return this.replayMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Replay> finds(Replay r) {
        return this.replayMapper.select(r);
    }


}
