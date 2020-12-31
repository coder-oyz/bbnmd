package com.yc.bbnmd1.service;

import com.yc.bbnmd1.entity.User;

public interface UserService {



    /**
     * 用户注册
     *
     * @param user
     */
    public void save(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    public void delete(Integer id);

    /**
     * 根据id查用户信息
     *
     * @param id
     * @return
     */
    public User findById(Integer id);

    /**
     * 查找用户
     * @param uname
     * @param upwd
     * @return
     */
    public User findOne(String uname,String upwd);


}
