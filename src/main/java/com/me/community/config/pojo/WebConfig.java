package com.me.community.config.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/24 15:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "community.web")
@Component
public class WebConfig {
    /**
     * cookie 的存放时间
     */
    int maxAge;
    /**
     * 访问的路径
     */
    String path;
}
