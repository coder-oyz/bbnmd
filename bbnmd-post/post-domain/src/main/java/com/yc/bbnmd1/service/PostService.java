package com.yc.bbnmd1.service;

import com.yc.bbnmd1.entity.Replay;
import com.yc.bbnmd1.entity.Topic;

public interface PostService {
    /**
     * 发布帖子
     * @param topic
     */
    public void postTopic(Topic topic);

    /**
     * 发布评论
     * @param replay
     */
    public void postReplay(Replay replay);

    /**
     * 更新回复
     * @param replay
     * @return
     */
    public int update(Replay replay);

}
