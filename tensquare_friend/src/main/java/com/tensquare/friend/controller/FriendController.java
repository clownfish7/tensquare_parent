package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yzy
 * @classname FriendController
 * @description TODO
 * @create 2019-07-16 15:09
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserClient userClient;

    /**
     * 添加好友或者非好友
     * @return
     */
    @PutMapping("/like/{friendId}/{type}")
    public Result addFriend(@PathVariable String friendId, @PathVariable String type) {
        //验证是否登录，并且拿到当前登录用户的id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            //当前用户没有user角色
            return new Result(false, StatusCode.ERROR, "权限不足");
        }
        //得到当前登录用户的Id
        String userId = claims.getId();
        //判断是添加好友还是添加非好友
        if (!StringUtils.isEmpty(type)) {
            if (type.equals("1")) {
                //添加好友
                int flag = friendService.addFriend(userId,friendId);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "不能重复添加好友");
                }
                if (flag == 1) {
                    userClient.updateFansAndFollowCount(userId, friendId, 1);
                    return new Result(false, StatusCode.ERROR, "添加成功");
                }
            } else if (type.equals("2")) {
                //添加非好友
                int flag = friendService.addNoFriend(userId,friendId);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "不能重复添加非好友");
                }
                if (flag == 1) {
                    return new Result(false, StatusCode.ERROR, "添加成功");
                }
            }
            return new Result(false, StatusCode.ERROR, "参数异常");
        } else {
            return new Result(false, StatusCode.ERROR, "参数异常");
        }

    }

    @DeleteMapping("/{friendid}")
    public Result deleteFriend(@PathVariable String friendid) {
        //验证是否登录，并且拿到当前登录用户的id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            //当前用户没有user角色
            return new Result(false, StatusCode.ERROR, "权限不足");
        }
        //得到当前登录用户的Id
        String userId = claims.getId();
        friendService.deleteFriend(userId, friendid);
        userClient.updateFansAndFollowCount(userId, friendid, -1);
        return new Result(true, StatusCode.OK, "删除成功");    }
}
