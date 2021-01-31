package com.me.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/31 11:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment implements Serializable {
    /**
     * 主键id
     */
    private long id;
    /**
     * 父级评论的id
     * post
     */
    private long parentId;
    /**
     * 评论的类型，是一级评论还是二级评论
     * post
     */
    private int type;
    /**
     * 评论用户的id
     * session
     */
    private long commentator;
    /**
     * 创建的日期
     */
    private String created;
    /**
     * 修改时间
     */
    private long modified;
    /**
     *  点赞数
     */
    private long likeCount;
    /**
     * post
     * 评论的内容
     */
    private String content;
}
