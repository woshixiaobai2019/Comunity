package com.me.community.service.impl;

import com.me.community.config.pojo.PaginationConfig;
import com.me.community.dao.QuestionMapper;
import com.me.community.dao.TagMapper;
import com.me.community.dao.UserMapper;
import com.me.community.dto.Pagination;
import com.me.community.dto.QuestionDto;
import com.me.community.pojo.Question;
import com.me.community.service.QuestionService;
import com.me.community.utils.PaginationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/25 19:16
 */
@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    PaginationConfig paginationConfig;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PaginationUtils paginationUtils;
    @Override
    public void save(Question question) {
        question.setCreated(DateUtils.format(new Date(), Locale.CHINA));
        question.setModified(System.currentTimeMillis());
        log.info("用户{}保存问题:{}",question.getCreator(),question.getTitle());
        questionMapper.save(question);
        //在保存问题后悔自动返回问题的主键id
        for (String tag : question.getTags()) {
            tagMapper.save(question.getId(),tag);
        }
    }


    @Override
    public Pagination showQuestions(Long uid, int currentPage) {

        int totalCount = questionMapper.totalCount(uid);
        int offset = (currentPage-1)*paginationConfig.getPageSize();
        List<QuestionDto> questions= questionMapper.findQuestions(uid,offset,paginationConfig.getPageSize());
        Pagination pagination = paginationUtils.getPagination(currentPage, paginationConfig.getPageSize(), paginationConfig.getMaxPageNum(),totalCount);
        pagination.setQuestions(questions);
        log.info("获取{}页数据,共{}条",currentPage,totalCount);
        return pagination;
    }
}
