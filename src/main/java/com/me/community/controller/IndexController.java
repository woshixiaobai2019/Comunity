package com.me.community.controller;

import com.me.community.config.pojo.GithubConfig;
import com.me.community.dto.GithubDto;
import com.me.community.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 17:43
 */
@Controller
public class IndexController {
    @Autowired
    GithubConfig githubConfig;
    @GetMapping({"/","/index","/index.html"})
    public String index(){
        return "index";
    }
    @ResponseBody
    @GetMapping("/index/init")
    public ResponseData init(){
        ResponseData data = new ResponseData();
        GithubDto dto = GithubDto.builder().clientId(githubConfig.getClientId()).
                redirectUri(githubConfig.getRedirectUri())
                .scope(githubConfig.getScope())
                .build();
        data.setResponse(dto);
        return data;
    }
    @RequestMapping("/exception")
    public String exception(){
        return "exception";
    }
}
