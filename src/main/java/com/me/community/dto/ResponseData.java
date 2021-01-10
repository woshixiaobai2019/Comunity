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
    private Object response;
}
