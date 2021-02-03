package com.me.community.service.impl;

import com.me.community.config.pojo.PaginationConfig;
import com.me.community.dao.QuestionMapper;
import com.me.community.dao.TagMapper;
import com.me.community.dao.UserMapper;
import com.me.community.dto.Pagination;
import com.me.community.dto.QuestionDetailDto;
import com.me.community.dto.QuestionDto;
import com.me.community.dto.QuestionEditDto;
import com.me.community.exception.BusinessException;
import com.me.community.pojo.Question;
import com.me.community.service.QuestionService;
import com.me.community.utils.PaginationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.DateUtils;

import java.text.SimpleDateFormat;
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
    @Transactional(rollbackFor = Exception.class)
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
        pagination.setObjects(questions);
        log.debug("获取{}页数据,共{}条",currentPage,totalCount);
        return pagination;
    }

    @Override
    public QuestionDetailDto findQuestionDetailById(int id, Long uid) {
        QuestionDetailDto questionDetailDto = questionMapper.findQuestionDetailById(id);
        if (questionDetailDto == null){
            throw new BusinessException("该问题未找到或已被删除");
        }
        updateViewCount(id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        questionDetailDto.setModified(dateFormat.format(Long.parseLong(questionDetailDto.getModified())));
        questionDetailDto.setEditable(questionDetailDto.getUid()==uid);
        return questionDetailDto;
    }

    @Override
    public QuestionEditDto findQuestionById(int id) {
        QuestionEditDto question = questionMapper.findQuestionById(id);
        //如果为空就处理异常
        if (question == null){
            throw new BusinessException("该问题未找到或已被删除");
        }
        List<String> tags = tagMapper.findTagById(id);
        question.setTags(tags);
        return question;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Question question) {
        // TODO: 2021/1/29 需要更新问题的标签
        question.setModified(System.currentTimeMillis());
        questionMapper.update(question);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateViewCount(int id) {
        questionMapper.updateViewCount(id);
    }
}
