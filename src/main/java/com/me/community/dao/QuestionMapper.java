package com.me.community.dao;

import com.me.community.dto.QuestionDto;
import com.me.community.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询总共的问题数量
     * @return
     */
    int totalCount();

    /**
     * 查询当前页的数据
     * @param offset 偏移
     * @param pageSize 每一页的数据条数
     * @return 当前页的数据
     */
    List<QuestionDto> findQuestions(@Param("offset") int offset, @Param("pageSize") int pageSize);
}
