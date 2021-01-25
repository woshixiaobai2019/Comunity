package com.me.community.service;

import com.me.community.pojo.User;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/24 14:53
 */
public interface UserService {
    /**
     * 创建用户
     * @param user 用户对象
     */
    void create(User user);

    /**
     * 根据用户的token查找用户
     * @param token 用户的token
     * @return 用户实体
     */
    User findUserByToken(String token);
}
