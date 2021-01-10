package com.me.community.exception;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 22:10
 */
public class GitAuthorizedException extends BusinessException{
    public GitAuthorizedException() {
    }

    public GitAuthorizedException(String msg) {
        super(msg);
    }
}
