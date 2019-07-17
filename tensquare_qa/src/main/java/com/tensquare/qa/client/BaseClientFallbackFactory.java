package com.tensquare.qa.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author yzy
 * @classname BaseClientFallbackFactory
 * @description TODO
 * @create 2019-07-16 14:20
 */
@Component // 不要忘记添加，不要忘记添加
public class BaseClientFallbackFactory implements BaseClient {

    @Override
    public Result findById(String labelId) {
        return new Result(StatusCode.ERROR,false,"熔断器hystrix");
    }
}
