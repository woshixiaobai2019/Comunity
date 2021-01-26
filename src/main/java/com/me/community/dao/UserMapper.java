package com.me.community.dao;

import com.me.community.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/24 14:54
 */
@Mapper
public interface UserMapper {
    /**
     * 根据github id查询用户
     * @param gitId github id
     * @return 用户实体
     */
    User findUserByGitId(@Param("gitId") String gitId);

    /**
     * 创建新用户
     * @param user 用户实体
     */
    void create(User user);

    /**
     * 更新用户的modified
     * @param user 用户实体
     */
    void updateModified(User user);

    /**
     * 根据token查找用户
     * @param token 用户的token
     * @return 用户实体
     */
    User findUserByToken(@Param("token") String token);
}
