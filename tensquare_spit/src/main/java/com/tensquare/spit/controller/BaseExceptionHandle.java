package com.tensquare.spit.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yzy
 * @classname BaseExceptionHandle
 * @description TODO
 * @create 2019-06-27 16:53
 */
@ControllerAdvice
public class BaseExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(StatusCode.ERROR,false,"执行出错");
    }

}
