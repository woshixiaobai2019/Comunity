package com.me.community.dao;

import com.me.community.dto.CommentDto;
import com.me.community.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    Comment findCommentById(@Param("parentId") long parentId);

    /**
     * 查询问题的评论数
     *
     * @param type 评论的类型
     * @param qId parentId
     * @return 评论数
     */
    int totalCount(@Param("type") long type, @Param("qId")long qId);

    /**
     * 获取当前页的评论
     * @param qId parentId
     * @param offset 偏移量
     * @param pageSize 每一页的数据条数
     * @param type 评论的类型
     * @return 当前页的评论内容
     */
    List<CommentDto> findComments(@Param("qId") long qId, @Param("offset") int offset, @Param("pageSize") int pageSize, @Param("type") long type);

    /**
     * 更新评论数
     * @param parentId 评论的父级ID
     */
    void updateCommentCount(@Param("parentId") long parentId);

    /**
     * 查询一条评论的所有子评论
     * @param parentId 查询的评论的id
     * @return 所有子评论
     */
    List<CommentDto> findSecondComments(@Param("parentId") long parentId);
}
