package com.tensquare.search.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yzy
 * @classname BaseExceptionHandle
 * @description TODO
 * @create 2019-07-05 16:35
 */
@ControllerAdvice
public class BaseExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error() {
        return new Result(StatusCode.ERROR, false, "执行出错");
    }
}
