package cn.edu.ustc.wsim.service;

import java.util.List;

import cn.edu.ustc.wsim.bean.FriendRequest;
import cn.edu.ustc.wsim.bean.User;

public interface FriendRequestService extends BaseService {
	
	public List<FriendRequest> getUndealFriendRequests(User user);

	public FriendRequest get(User requester, User responder);
	
}
