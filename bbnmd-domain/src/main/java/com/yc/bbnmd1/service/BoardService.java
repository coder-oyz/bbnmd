package com.yc.bbnmd1.service;
import com.yc.bbnmd1.entity.Board;

public interface BoardService {
    /**
     * 新建板块
     *
     * @param board
     */
    public void save(Board board);

    /**
     * 删除板块
     *
     * @param id
     */
    public void delete(Integer id);

    /**
     * 根据id查板块信息
     *
     * @param id
     * @return
     */
    public Board findOne(Integer id);
}
