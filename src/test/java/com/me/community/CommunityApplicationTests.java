package com.me.community;

import com.me.community.pojo.Question;
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
    @Test
    void contextLoads() {
    }
    @Test
    void dateFormatTest(){
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(dateFormat.format(new Date()));
//        System.out.println(System.currentTimeMillis());
        System.out.println(DateUtils.format(System.currentTimeMillis(), Locale.CHINA));
    }
    @Test
    void saveQuestion(){
        questionService.save(Question.builder().title("test").description("test").tags(Arrays.asList("test1","test2")).creator(1L).build());

    }
    @Test
    void testPagination(){
        System.out.println(questionService.showQuestions(1));
    }
}
