package com.me.community;

import com.me.community.pojo.Question;
import com.me.community.service.CommentService;
import com.me.community.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.datetime.DateFormatter;
import org.thymeleaf.util.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    QuestionService questionService;
    @Autowired
    CommentService commentService;
    @Test
    void contextLoads() {
    }
    @Test
    void dateFormatTest(){
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(dateFormat.format(new Date()));
//        System.out.println(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateFormat.format(System.currentTimeMillis());
        System.out.println(dateStr);
//        System.out.println(DateUtils.format(System.currentTimeMillis(), Locale.CHINA));
    }
    @Test
    void saveQuestion(){
        questionService.save(Question.builder().title("test").description("test").tags(Arrays.asList("test1","test2")).creator(1L).build());

    }
    @Test
    void testPagination(){
        System.out.println(questionService.showQuestions(2L,1));
    }
    @Test
    void testQuestionDetail(){
        System.out.println(questionService.findQuestionDetailById(5, 2L));
    }
    @Test
    void editQuestion(){
        System.out.println(questionService.findQuestionById(5));
    }
    @Test
    void commentTest(){
        System.out.println(commentService.getFirstLevelComment(4, 1));
    }
    @Test
    void seCommentTest(){
        System.out.println(commentService.getSecondLevelComment(1));
    }
}
