package com.me.community.config.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 18:13
 */
@Component
@ConfigurationProperties(prefix = "community.github")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubConfig {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String login;
    private String scope;
    private String state;
    private String allowSignup;
}
