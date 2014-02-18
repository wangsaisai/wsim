package cn.edu.ustc.wsim.dao;

import java.util.List;

import cn.edu.ustc.wsim.bean.Friend;
import cn.edu.ustc.wsim.bean.FriendGroup;

public interface FriendDao extends BaseDao {
	
	public List<Friend> getFriendsOfFriendGroup(FriendGroup friendGroup);
	
}
