package com.me.community.enums;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/2/1 11:42
 */
public enum  CommentType {
    /**
     * 一级评论，parentId为questionId
     */
    FIRST_LEVEL(0),
    /**
     * 二级评论，parentId为评论的id
     */
    SECOND_LEVEL(1);
    long type;
    CommentType(long type){
        this.type = type;
    }

    public long getType() {
        return type;
    }
}
