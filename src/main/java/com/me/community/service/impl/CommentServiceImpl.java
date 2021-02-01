package com.me.community.service.impl;

import com.me.community.dao.CommentMapper;
import com.me.community.dao.QuestionMapper;
import com.me.community.dto.QuestionEditDto;
import com.me.community.enums.CommentType;
import com.me.community.exception.BusinessException;
import com.me.community.pojo.Comment;
import com.me.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.util.Date;
import java.util.Locale;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/31 11:57
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Override
    public void save(Comment comment) {
        comment.setCreated(DateUtils.format(new Date(), Locale.CHINA));
        comment.setModified(System.currentTimeMillis());
        //判断评论的类型
        if (comment.getType()== CommentType.FIRST_LEVEL.getType()){
            //查询数据库中是否有相应的问题
            QuestionEditDto question = questionMapper.findQuestionById(comment.getParentId());
            if (question==null){
                throw new BusinessException("评论的问题不存在");
            }
            commentMapper.save(comment);
            questionMapper.updateComment(comment.getParentId());
        }else if (comment.getType() == CommentType.SECOND_LEVEL.getType()){
            Comment commentById = commentMapper.findCommentById(comment.getParentId());
            if (commentById==null){
                throw new BusinessException("您回复的评论不存在");
            }
            commentMapper.save(comment);
        }else{
            throw new BusinessException("未知的评论类型");
        }
    }
}
