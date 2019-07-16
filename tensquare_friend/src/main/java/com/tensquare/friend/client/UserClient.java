package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author yzy
 * @classname UserClient
 * @description TODO
 * @create 2019-07-16 17:11
 */
@FeignClient("tensquare-user")
public interface UserClient {

    @PutMapping("/user/{userid}/{friendid}/{x}")
    public void updateFansAndFollowCount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid, @PathVariable("x") int x);
}
