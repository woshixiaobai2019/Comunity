package com.me.community.service.impl;

import com.me.community.dao.UserDao;
import com.me.community.pojo.User;
import com.me.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.util.Date;
import java.util.Locale;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/24 14:54
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public void create(User user) {
        //1.判断数据库中是否已经存在该用户,存在就不新创建了
        User query = userDao.findUserByGitId(user.getGitId());
        if (query==null){
            user.setCreatedTime(DateUtils.format(new Date(), Locale.CHINA));
            log.info("创建新用户:{}",user.getUsername());
            userDao.create(user);
        }else{
            userDao.updateModified(user);
        }

    }

    @Override
    public User findUserByToken(String token) {
        User user;
        if (token ==null){
            return null;
        }else{
            user = userDao.findUserByToken(token);
        }
        log.debug("根据token:{} 查询到的用户为:{}",token,user==null?null:user.getUsername());
        return user;
    }
}
