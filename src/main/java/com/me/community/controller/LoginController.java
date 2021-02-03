package com.me.community.controller;

import com.me.community.consts.WebConst;
import com.me.community.dto.GitAccept;
import com.me.community.dto.ResponseData;
import com.me.community.dto.UserInfo;
import com.me.community.pojo.User;
import com.me.community.service.UserService;
import com.me.community.utils.GitHubUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
    @Autowired
    UserService userService;
    @RequestMapping("/authorizedCallback")
    public String authorized( @RequestParam(name = "code") String code, HttpServletResponse response){
        GitAccept accept = gitHubUtils.authorized(code);
        log.info("认证结果->{}",accept.toString());
        UserInfo user = gitHubUtils.getUser(accept.getAccess_token());
        String loginToken = UUID.randomUUID().toString();
        User build = User.builder().gitId(user.getId()).username(user.getLogin()).avatarUrl(user.getAvatar_url())
                .modified(Long.toString(System.currentTimeMillis())).token(loginToken).build();
        userService.create(build);
        //隐藏用户的Id
        user.setId(null);
        setAttribute2Cookie(response, WebConst.USER_COOKIE_PREFIX,loginToken);
        return "redirect:/index";
    }
    @ResponseBody
    @GetMapping("/toLogin")
    public ResponseData loginUp(HttpServletRequest request){
        String token = getAttributeFromCookie(request, WebConst.USER_COOKIE_PREFIX);
        User user = userService.findUserByToken(token);
        if (user == null){
            return ResponseData.builder().response(null).build();
        }
        setAttribute2Session(request,WebConst.USER_SESSION_PREFIX,user.getId());
        UserInfo build = UserInfo.builder().avatar_url(user.getAvatarUrl()).login(user.getUsername()).build();
        return ResponseData.builder().response(build).build();
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        setAttribute2Session(request,WebConst.USER_SESSION_PREFIX,null);
        setAttribute2Cookie(response,WebConst.USER_COOKIE_PREFIX,null);
        return "redirect:/index";
    }
}
