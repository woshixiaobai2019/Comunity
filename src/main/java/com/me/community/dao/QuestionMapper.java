package com.me.community.dao;

import com.me.community.dto.QuestionDetailDto;
import com.me.community.dto.QuestionDto;
import com.me.community.dto.QuestionEditDto;
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
     * 查找用户的所有问题
     * @param uid 用户的id
     * @param offset 偏移
     * @param pageSize 每一页的数据
     * @return 当前页的数据
     */
    List<QuestionDto> findQuestions(@Param("creator") Long uid, @Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 查找问题的总数量
     * @param uid 用户的id，如果为null或者空就查找所有的问题
     * @return 当前用户的问题数量
     */
    int totalCount(@Param("creator") Long uid);

    /**
     * 根据问题的id 查找问题详情
     * @param id 问题的id
     * @return 问题的详情
     */
    QuestionDetailDto findQuestionDetailById(@Param("id") int id);

    /**
     * 根据问题的id查找问题
     * @param id 问题的id
     * @return 问题的对象
     */
    QuestionEditDto findQuestionById(@Param("id") int id);

    /**
     * 更新问题
     * @param question 问题对象
     */
    void update(Question question);

    /**
     * 更新阅读数
     * @param id 问题的id
     */
    void updateViewCount(@Param("id") int id);
}
