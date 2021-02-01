package com.me.community.dao;

import com.me.community.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据id查找评论
     * @param parentId 该评论的父级评论id
     * @return 对应的评论
     */
    Comment findCommentById(@Param("parendId") long parentId);
}
