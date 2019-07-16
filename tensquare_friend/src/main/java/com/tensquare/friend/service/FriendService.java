package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.entity.Friend;
import com.tensquare.friend.entity.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yzy
 * @classname FriendService
 * @description TODO
 * @create 2019-07-16 15:24
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userId, String friendId) {
        //先判断userId到friendId是否有数据，有就是重复添加 return 0
        Friend friend = friendDao.findByUseridAndFriendid(userId, friendId);
        if (friend != null) {
            return 0;
        }
        //直接添加好友,让好友表中userId到friendId方向的type = 0
        friend = new Friend();
        friend.setUserid(userId);
        friend.setFriendid(friendId);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断friendId到userId是否有数据，如果有，双方状态都改为1
        if (friendDao.findByUseridAndFriendid(friendId, userId) != null) {
            //将双方状态置为1
            friendDao.updateIslike("1", userId, friendId);
            friendDao.updateIslike("1", friendId, userId);
        }
        return 1;
    }

    public int addNoFriend(String userId, String friendId) {
        //先判断是否已经是非好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userId,friendId);
        if (noFriend != null) {
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendId);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userId, String friendid) {
        //删除好友表中user到friend的这条数据
        friendDao.deleteFriend(userId, friendid);
        //更新friend到user的islike为0
        friendDao.updateIslike("0", friendid, userId);
        //非好友中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}
