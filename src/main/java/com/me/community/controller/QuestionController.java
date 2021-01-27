package com.me.community.controller;

import com.me.community.config.pojo.PaginationConfig;
import com.me.community.consts.WebConst;
import com.me.community.dto.Pagination;
import com.me.community.dto.ResponseData;
import com.me.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
