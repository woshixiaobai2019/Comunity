package com.me.community.service;

import com.me.community.dto.CommentDto;
import com.me.community.dto.Pagination;
import com.me.community.pojo.Comment;

import java.util.List;

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

    /**
     * 获取一个评论的二级评论
     * @param parentId 父级评论的id
     * @return 一条评论的所有子评论
     */
    List<CommentDto> getSecondLevelComment(long parentId);
}
