package com.me.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/29 17:17
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class QuestionEditDto {
    /**
     * 问题的id
     */
    private Integer id;
    /**
     * 问题的标题
     */
    private String title;
    /**
     * 问题的描述
     */
    private String description;
    /**
     * 标签
     */
    private List<String> tags;
}
