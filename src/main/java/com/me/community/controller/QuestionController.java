package com.me.community.controller;

import com.me.community.config.pojo.PaginationConfig;
import com.me.community.consts.WebConst;
import com.me.community.dto.*;
import com.me.community.pojo.Question;
import com.me.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/26 12:57
 */
@Controller
public class QuestionController extends BaseController{
    @Autowired
    QuestionService questionService;

    @GetMapping("/questionShow")
    public String questionShow(){
        return "questionShow";
    }
    @PostMapping("/getQuestion")
    @ResponseBody
    public ResponseData showQuestions(@RequestParam(name = "currentPage",defaultValue = "1") int currentPage){
        ResponseData response = ResponseData.builder().timeStamp(System.currentTimeMillis()).build();
        if (currentPage<1){
            response.setStatus(WebConst.ERROR);
            response.setResponse("请求错误");
        }else{
            Pagination pagination = questionService.showQuestions(null,currentPage);
            response.setResponse(pagination);
            response.setStatus(WebConst.OK);
        }
        return response;
    }
    @GetMapping("/questionDetail")
    public String questionDetail(){
        return "question-detail";
    }
    @ResponseBody
    @GetMapping("/getQuestionDetail/{id}")
    public ResponseData getQuestionDetail(@PathVariable int id, HttpServletRequest request){
        ResponseData response = ResponseData.builder().timeStamp(System.currentTimeMillis()).build();
        response.setStatus(WebConst.OK);
        QuestionDetailDto questionDetailDto = questionService.findQuestionDetailById(id,(Long)getAttributeFromSession(request,WebConst.USER_SESSION_PREFIX));
        response.setResponse(questionDetailDto);
        return response;
    }
    @GetMapping("/editQuestion/{id}")
    @ResponseBody
    public ResponseData editQuestion(@PathVariable(name = "id") int id){
        ResponseData build = ResponseData.builder().timeStamp(System.currentTimeMillis()).build();
        QuestionEditDto question = questionService.findQuestionById(id);
        build.setStatus(WebConst.OK);
        build.setResponse(question);
        return build;
    }
}
