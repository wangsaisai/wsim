package cn.edu.ustc.wsim.dao;

import java.util.List;

import cn.edu.ustc.wsim.bean.FriendRequest;
import cn.edu.ustc.wsim.bean.User;


public interface FriendRequestDao extends BaseDao {
	
	public List<FriendRequest> getUndealFriendRequests(User user);

	public FriendRequest get(User requester, User responder);
	
}
