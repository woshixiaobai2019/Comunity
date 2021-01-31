package com.me.community.service;

import com.me.community.pojo.Comment;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/31 11:57
 */
public interface CommentService {

    /**
     * 保存评论
     * @param comment 评论对象
     */
    void save(Comment comment);
}
