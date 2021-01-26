package com.me.community.dao;

import com.me.community.dto.QuestionDto;
import com.me.community.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

//    /**
//     * 查询总共的问题数量
//     * @return
//     */
//    int totalCount();
//
//    /**
//     * 查询当前页的数据
//     * @param offset 偏移
//     * @param pageSize 每一页的数据条数
//     * @return 当前页的数据
//     */
//    List<QuestionDto> findQuestions(@Param("offset") int offset, @Param("pageSize") int pageSize);

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
}
