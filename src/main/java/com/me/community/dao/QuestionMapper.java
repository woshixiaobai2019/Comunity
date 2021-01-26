package com.me.community.dao;

import com.me.community.pojo.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/25 19:16
 */
@Mapper
public interface QuestionMapper {
    /**
     * 保存问题
     * @param question 问题对象实体
     */
    void save(Question question);
}
