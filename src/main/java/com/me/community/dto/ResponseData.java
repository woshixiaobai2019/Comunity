package com.me.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 18:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData implements Serializable {
    private String status;
    private Object response;
    private long timeStamp;
    /**
     * error code,只有500 和 501两个数据，如果是500就需要用错误页面展示，如果是501就是用alert显示
     */
    private int code;
}
