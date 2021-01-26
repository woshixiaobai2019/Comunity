package com.me.community.config.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/26 14:30
 */
@ConfigurationProperties(prefix = "community.pagination")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationConfig {
    private int pageSize;
    private int maxPageNum;
}
