package com.yc.bbnmd1.service;

import com.yc.bbnmd1.entity.Topic;

public interface TopicService {
    /**
     * 发布帖子
     *
     * @param topic
     */
    public void save(Topic topic);

    /**
     * 删除帖子
     *
     * @param id
     */
    public void delete(Integer id);

    /**
     * 根据id查帖子信息
     *
     * @param id
     * @return
     */
    public Topic findOne(Integer id);
}
