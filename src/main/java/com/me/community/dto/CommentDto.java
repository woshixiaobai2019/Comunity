package com.me.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/2/3 14:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto implements Serializable {
    /**
     * 评论的主键
     */
    private long id;
    /**
     * 评论的用户名
     */
    private String username;
    /**
     * 用户的头像
     */
    private String avatarUrl;

    /**
     * 评论的创建日期
     */
    private String modified;
    /**
     * 评论的点赞数
     */
    private long likeCount;
    /**
     * 评论的内容
     */
    private String content;
    /**
     * 评论的评论数
     */
    private int commentCount;

}
