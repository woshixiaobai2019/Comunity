package com.me.community.controller;

import com.me.community.consts.WebConst;
import com.me.community.dto.Pagination;
import com.me.community.dto.ResponseData;
import com.me.community.service.QuestionService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/26 21:45
 */
@Controller
public class ProfileController extends BaseController{
    @Autowired
    QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action){
        if ("reply".equals(action)){
            return "profile-reply";
        }else{
            return "profile-question";
        }
    }
    @PostMapping("/profile/question")
    @ResponseBody
    public ResponseData myQuestions(HttpServletRequest request,@RequestParam(name = "currentPage",defaultValue = "1") int currentPage){
        Long uid = (Long) getAttributeFromSession(request, WebConst.USER_SESSION_PREFIX);
        ResponseData build = ResponseData.builder().timeStamp(System.currentTimeMillis()).build();

        if (uid==null || currentPage<1){
            build.setStatus(WebConst.ERROR);
            build.setResponse("错误的请求");
        }else{
            build.setStatus(WebConst.OK);
            Pagination pagination = questionService.showQuestions(uid,currentPage);
            build.setResponse(pagination);
        }
        return build;
    }
}
