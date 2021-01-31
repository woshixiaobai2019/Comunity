package com.me.community.controller;

import com.me.community.consts.WebConst;
import com.me.community.dto.ResponseData;
import com.me.community.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/30 11:33
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData handle(Exception exception){
        ResponseData build = ResponseData.builder().timeStamp(System.currentTimeMillis()).status(WebConst.ERROR).code(500).build();
        if (exception instanceof  BusinessException){
            log.error(exception.getMessage());
            build.setResponse(exception.getMessage());
        }else{
            log.error(exception.getMessage(),exception);
            build.setResponse("内部错误");
        }
        return build;
    }

}
