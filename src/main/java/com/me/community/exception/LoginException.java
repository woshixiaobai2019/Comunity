package com.me.community.exception;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/30 11:41
 */
public class LoginException extends BusinessException{
    private String msg;
    LoginException(){

    }
    public LoginException(String msg){
        super(msg);
    }
}
