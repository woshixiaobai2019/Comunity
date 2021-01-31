package com.me.community.dao;

import com.me.community.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/31 11:57
 */
@Mapper
public interface CommentMapper {
    /**
     * 保存评论
     * @param comment 评论对象
     */
    void save(Comment comment);
}
