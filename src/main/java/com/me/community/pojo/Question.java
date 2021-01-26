package com.me.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/25 18:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question implements Serializable {
    /**
     * 问题的id
     */
    private int id;
    /**
     * 问题的标题
     */
    private String title;
    /**
     * 问题的描述
     */
    private String description;
    /**
     * 问题的创建日期
     */
    private String created;
    /**
     * 问题的修改时间
     */
    private long modified;
    /**
     * 问题创建者的用户id
     */
    private long creator;
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
     * 问题的标签
     */
    private List<String> tags;
}
