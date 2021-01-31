package com.me.community.service.impl;

import com.me.community.dao.CommentMapper;
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
    @Override
    public void save(Comment comment) {
        comment.setCreated(DateUtils.format(new Date(), Locale.CHINA));
        comment.setModified(System.currentTimeMillis());
        commentMapper.save(comment);
    }
}
