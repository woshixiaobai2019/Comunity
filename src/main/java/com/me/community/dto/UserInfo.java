package com.me.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 22:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo implements Serializable {
    /**
     * 登录的用户字段
     */
    private String login;
    /**
     * 用户的githubId
     */
    private String id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 头像地址
     */
    private String avatar_url;
}
