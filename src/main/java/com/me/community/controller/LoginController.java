package com.me.community.controller;

import com.me.community.config.pojo.GithubConfig;
import com.me.community.dto.GitAccept;
import com.me.community.dto.ResponseData;
import com.me.community.dto.UserInfo;
import com.me.community.utils.GitHubUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 21:32
 */
@Controller
@Slf4j
public class LoginController extends BaseController{
    @Autowired
    GitHubUtils gitHubUtils;
    @RequestMapping("/github/authorizedCallback")
    public String authorized(HttpServletRequest request, @RequestParam(name = "code") String code){
        GitAccept accept = gitHubUtils.authorized(code);
        log.info("认证结果->{}",accept.toString());
        UserInfo user = gitHubUtils.getUser(accept.getAccess_token());
        //隐藏用户的Id
        user.setId(null);
        setAttribute2Session(request,"user",user);
        return "redirect:/index";
    }
    @ResponseBody
    @GetMapping("/toLogin")
    public ResponseData loginUp(HttpServletRequest request){
        return ResponseData.builder().response(getAttributeFromSession(request,"user")).build();
    }
}
