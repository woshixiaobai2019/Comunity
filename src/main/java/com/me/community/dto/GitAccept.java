package com.me.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 21:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitAccept implements Serializable {
    private String access_token;
    private String scope;
    private String token_type;
}
