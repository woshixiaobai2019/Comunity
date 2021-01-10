package com.me.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 18:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GithubDto implements Serializable {
    private String clientId;
    private String redirectUri;
    private String scope;
}
