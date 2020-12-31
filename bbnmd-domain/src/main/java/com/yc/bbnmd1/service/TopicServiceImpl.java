package com.yc.bbnmd1.service;

import com.yc.bbnmd1.dao.impl.TopicMapper;
import com.yc.bbnmd1.entity.Topic;
import com.yc.bbnmd1.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired(required = false)
    private TopicMapper topicMapper;

    @Override
    public void save(Topic topic) {
        //String title,String content, Integer uid, Integer bid
        Topic topicBean = new Topic(topic.getTitle(),topic.getContent(),topic.getUid(),topic.getBid());
        topicBean.setPublishtime(CommonUtils.formatDateTime(new Date()));
        topicBean.setModifytime(CommonUtils.formatDateTime(new Date()));
        this.topicMapper.insert(topicBean);
        topic.setTid(topicBean.getTid());
    }

    @Override
    public void delete(Integer id) {
        this.topicMapper.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Topic findOne(Integer id) {
        return this.topicMapper.selectByPrimaryKey(id);

    }


}
