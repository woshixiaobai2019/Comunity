package com.me.community.service;

import com.me.community.dto.Pagination;
import com.me.community.pojo.Question;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/25 19:15
 */
public interface QuestionService {

    /**
     * 保存问题
     * @param question 问题的实体类
     */
    void save(Question question);

    /**
     * 查询当前页的数据
     * @param currentPage 当前的页数
     * @return 当前页的数据
     */
    Pagination showQuestions(int currentPage);
}
