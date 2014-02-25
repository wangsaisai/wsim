package cn.edu.ustc.wsim.service.impl;

import java.util.List;

import cn.edu.ustc.wsim.bean.FriendRequest;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.FriendRequestDao;
import cn.edu.ustc.wsim.service.FriendRequestService;

public class FriendRequestServiceImpl extends BaseServiceImpl implements FriendRequestService {

	private FriendRequestDao friendRequestDao;
	
	public FriendRequestDao getFriendRequestDao() {
		return friendRequestDao;
	}

	public void setFriendRequestDao(FriendRequestDao friendRequestDao) {
		this.friendRequestDao = friendRequestDao;
	}

	@Override
	public long count() {
		return friendRequestDao.count();
	}

	@Override
	public FriendRequest get(int id) {
		return (FriendRequest) friendRequestDao.get(id);
	}
	
	@Override
	public boolean del(int id) {
		return friendRequestDao.del(id);
	}

	@Override
	public List<FriendRequest> getUndealFriendRequests(User user) {
		return friendRequestDao.getUndealFriendRequests(user);
	}

	@Override
	public FriendRequest get(User requester, User responder) {
		return friendRequestDao.get(requester, responder);
	}

}
