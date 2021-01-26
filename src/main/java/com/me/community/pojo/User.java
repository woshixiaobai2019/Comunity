package com.me.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/24 11:28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {
    /**
     * 用户的数据库id
     */
    private Long id;
    /**
     * 用户的github id
     */
    private String gitId;
    /**
     * 用户的token
     */
    private String token;
    /**
     * 用户的头像地址
     */
    private String avatarUrl;
    /**
     * 用户的用户名
     */
    private String username;
    /**
     * 用户的最近访问时间
     */
    private String modified;
    /**
     * 用户的创建时间
     */
    private String createdTime;

}
