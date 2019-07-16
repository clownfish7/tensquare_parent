package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yzy
 * @classname BaseExceptionController
 * @description TODO
 * @create 2019-06-20 17:15
 */

@RestControllerAdvice
public class BaseExceptionController {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        e.printStackTrace();
        return new Result(StatusCode.ERROR,false, e.getMessage());
    }
}
