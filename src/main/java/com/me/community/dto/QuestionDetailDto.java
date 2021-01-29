package com.me.community.dto;

import lombok.*;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/29 12:29
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class QuestionDetailDto extends QuestionDto{
    /**
     *用户的uid
     */
    private long uid;
    /**
     * 用户的用户名
     */
    private String username;
    /**
     * 是否可以编辑
     */
    private boolean editable;
    /**
     * 问题的修改时间
     */
    private String modified;
    @Builder(builderMethodName = "childBuilder")
    public QuestionDetailDto(Integer id, String title, String description, int commentCount, int viewCount, int likeCount, String avatarUrl, long uid, String username, boolean editable,String modified) {
        super(id, title, description, commentCount, viewCount, likeCount, avatarUrl);
        this.uid = uid;
        this.username = username;
        this.editable = editable;
        this.modified = modified;
    }
}
