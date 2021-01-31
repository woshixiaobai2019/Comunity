package com.me.community.exception;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 22:08
 */
public class BusinessException extends RuntimeException{
    public BusinessException(){

    }
    public BusinessException(String msg){
        super(msg);
    }
}
