package com.me.community.service.impl;

import com.me.community.config.pojo.PaginationConfig;
import com.me.community.dao.CommentMapper;
import com.me.community.dao.QuestionMapper;
import com.me.community.dto.CommentDto;
import com.me.community.dto.Pagination;
import com.me.community.dto.QuestionDto;
import com.me.community.dto.QuestionEditDto;
import com.me.community.enums.CommentType;
import com.me.community.exception.BusinessException;
import com.me.community.pojo.Comment;
import com.me.community.service.CommentService;
import com.me.community.utils.PaginationUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/31 11:57
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    PaginationConfig paginationConfig;
    @Autowired
    PaginationUtils paginationUtils;
    @Override
    @Transactional(rollbackFor = Exception.class)
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

    @Override
    public Pagination getFirstLevelComment(long qId, int currentPage) {
        int totalCount = commentMapper.totalCount(CommentType.FIRST_LEVEL.getType(),qId);
        int offset = (currentPage-1)*paginationConfig.getPageSize();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<CommentDto> comments= commentMapper.findComments(qId,offset,paginationConfig.getPageSize(),CommentType.FIRST_LEVEL.getType());
        List<CommentDto> collect = comments.stream().peek(commentDto -> commentDto.setModified(dateFormat.format(Long.parseLong(commentDto.getModified())))).collect(Collectors.toList());
        Pagination pagination = paginationUtils.getPagination(currentPage, paginationConfig.getPageSize(), paginationConfig.getMaxPageNum(),totalCount);
        pagination.setObjects(collect);
        log.debug("获取问题{}的{}页数据,共{}条",qId,currentPage,totalCount);
        return pagination;
    }
}
