package com.tensquare.friend.dao;

import com.tensquare.friend.entity.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yzy
 * @classname FriendDao
 * @description TODO
 * @create 2019-07-16 15:42
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String> {
    NoFriend findByUseridAndFriendid(String userId, String friendId);

}
