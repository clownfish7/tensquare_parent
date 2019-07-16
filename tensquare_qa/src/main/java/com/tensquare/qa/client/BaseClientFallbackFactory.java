package com.tensquare.qa.client;

import entity.Result;
import entity.StatusCode;
import feign.hystrix.FallbackFactory;

/**
 * @author yzy
 * @classname BaseClientFallbackFactory
 * @description TODO
 * @create 2019-07-16 14:20
 */
public class BaseClientFallbackFactory implements FallbackFactory<BaseClient> {

    @Override
    public BaseClient create(Throwable throwable) {
        return new BaseClient() {
            @Override
            public Result findById(String labelId) {
                return new Result(StatusCode.ERROR,false,"error");
            }
        };
    }
}
