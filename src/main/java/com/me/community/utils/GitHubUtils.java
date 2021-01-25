package com.me.community.utils;

import com.alibaba.fastjson.JSON;
import com.me.community.config.pojo.GithubConfig;
import com.me.community.dto.GitAccept;
import com.me.community.dto.UserInfo;
import com.me.community.exception.GitAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 22:02
 * 使用spring boot自带的RestTemplate作为HTTP请求工具
 */
@Slf4j
@Component
public class GitHubUtils {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GithubConfig githubConfig;
    public GitAccept authorized(String code){
        HttpHeaders headers = new HttpHeaders();
        List<MediaType> accept = new ArrayList<>();
        accept.add(MediaType.APPLICATION_JSON);
        headers.setAccept(accept);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        LinkedMultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("code",code);
        map.add("client_id",githubConfig.getClientId());
        map.add("client_secret",githubConfig.getClientSecret());
        HttpEntity<LinkedMultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<GitAccept> response = null;
        try {
            response = restTemplate.postForEntity(githubConfig.getAccessTokenUrl(), entity, GitAccept.class);
        } catch (RestClientException e) {
            log.error("认证失败",e);
            throw new GitAuthorizedException("认证失败");
        }
        return response.getBody();
    }
    public UserInfo getUser(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","token "+token);
        HttpEntity<Object> mapHttpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<UserInfo> response;
        try {
            response = restTemplate.exchange(githubConfig.getUserInfoUrl(), HttpMethod.GET, mapHttpEntity, UserInfo.class);
        } catch (RestClientException e) {
            log.error("获取用户信息失败",e);
            throw new GitAuthorizedException("获取用户信息失败");
        }
        return response.getBody();
    }
}
