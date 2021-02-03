package com.me.community.service;

import com.me.community.dto.Pagination;
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

    /**
     * 获取问题的一级评论
     *
     * @param qId 问题的id
     * @param currentPage 当前的页数
     * @return 当前页的评论
     */
    Pagination getFirstLevelComment(long qId, int currentPage);
}
