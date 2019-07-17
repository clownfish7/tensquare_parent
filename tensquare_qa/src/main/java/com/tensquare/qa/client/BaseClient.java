package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yzy
 * @classname BaseClient
 * @description TODO
 * @create 2019-07-16 14:04
 */

@FeignClient(value = "tensquare-base", fallback = BaseClientFallbackFactory.class)
public interface BaseClient {
    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId);
}
