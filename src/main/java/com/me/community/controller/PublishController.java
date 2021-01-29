package com.me.community.controller;

import com.me.community.consts.WebConst;
import com.me.community.dto.ResponseData;
import com.me.community.pojo.Question;
import com.me.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/25 17:37
 */
@Controller
@Slf4j
public class PublishController extends BaseController{
    @Autowired
    QuestionService questionService;
    @GetMapping("/publish")
    public String toPublish(){
        return "publish";
    }
    @PostMapping("/publish/{id}")
    @ResponseBody
    public ResponseData publish(HttpServletRequest request,
                                @PathVariable(name = "id") Integer id,
                                @RequestParam(name = "title",defaultValue = "") String title,
                                @RequestParam(name = "description",defaultValue = "") String description,
                                @RequestParam(name = "tags",defaultValue = "") String tags){
        ResponseData build = ResponseData.builder().timeStamp(System.currentTimeMillis()).build();
        log.debug("问题的标题:{},描述:{}...,标签:{}..",title,description.length()>5?description.substring(0,5):description,tags);
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(description) || StringUtils.isEmpty(tags)){
            build.setResponse("不满足提问题规范");
            build.setStatus(WebConst.ERROR);
        }else{
            List<String> list = Arrays.asList(tags.split(","));
            Long creator = (Long) getAttributeFromSession(request, WebConst.USER_SESSION_PREFIX);
            Question question = Question.builder().title(title).description(description).tags(list).creator(creator).build();
            if (id!=null){
                question.setId(id);
                questionService.update(question);
                build.setResponse("更新问题成功");
            }else{
                questionService.save(question);
                build.setResponse("提交问题成功");
            }
            build.setStatus(WebConst.OK);

        }
        return build;
    }
}
