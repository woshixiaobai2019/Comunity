package com.me.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/26 14:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {
    /**
     * 问题的id
     */
    private Integer id;
    /**
     * 问题的标题
     */
    private String title;
    /**
     * 问题的描述
     */
    private String description;
    /**
     * 问题的评论数
     */
    private int commentCount;
    /**
     * 问题的浏览数
     */
    private int viewCount;
    /**
     * 问题的获赞总数
     */
    private int likeCount;
    /**
     * 用户的头像地址
     */
    private String avatarUrl;
}
