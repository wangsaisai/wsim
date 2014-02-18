package cn.edu.ustc.wsim.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.UserDao;
import cn.edu.ustc.wsim.datastructure.OnlineUsers;
import cn.edu.ustc.wsim.enumerates.UserRelation;
import cn.edu.ustc.wsim.service.FriendGroupService;
import cn.edu.ustc.wsim.service.UserService;
import cn.edu.ustc.wsim.util.CheckEmail;

public class UserServiceImpl extends BaseServiceImpl implements UserService {

	private UserDao userDao;
	private FriendGroupService friendGroupService;
	
	public FriendGroupService getFriendGroupService() {
		return friendGroupService;
	}

	public void setFriendGroupService(FriendGroupService friendGroupService) {
		this.friendGroupService = friendGroupService;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public long count() {
		return userDao.count();
	}

	@Override
	public User get(int id) {
		return (User) userDao.get(id);
	}
	
	@Override
	public boolean del(int id) {
		return userDao.del(id);
	}

	@Override
	public List<User> getUsersByName(String name) {
		return userDao.getUsersByName(name);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User getUserByEmailAndPassoord(String email, String password) {
		return userDao.getUserByEmailAndPassoord(email, password);
	}

	@Override
	public boolean isLogin(User user) {
		return OnlineUsers.isLogin(user.getId());
	}

	@Override
	public boolean isFriend(User self, User other) {
//		for (FriendGroup fg : (List<FriendGroup>)this.getUserInfo(self).getFriendgroups()) {
//			if(fg.getFriends().contains(other))
//				return true;
//		}
//		return false;
//		for(Iterator iterator = this.getUserInfo(self).getFriendgroups().iterator(); iterator.hasNext();) {
//			System.out.println("      false    11111");
//			FriendGroup fg = (FriendGroup) iterator.next();
//			if(fg.getFriends().contains(other))
//				return true;
//		}
//		return false;
		
		return friendGroupService.isFriend(self, other);
	}

	@Override
	public UserRelation getRelation(User self, User other) {
		boolean b1 = this.isFriend(self, other);
		boolean b2 = this.isFriend(other, self);
		
		//注意NY 和YN不要混淆
		//YN单项好友，2是1的好友，1不是2的好友
		if(b1) {
			if(b2)
				return UserRelation.FRIEND;
			else
				return UserRelation.YN;
		} else {
			if(b2)
				return UserRelation.NY;
			else
				return UserRelation.NOTFRIEND;
		}
	}

	@Override
	public List<User> searchUser(String searchinfo) {
		CheckEmail ce = new CheckEmail();
		if(ce.isEmail(searchinfo)) {
			if(userDao.getUserByEmail(searchinfo) != null)
			{
				List users = new ArrayList<User>();
				users.add(userDao.getUserByEmail(searchinfo));
				return users;
			}
			else
				return null;
		}
		else
			return userDao.searchUserByName(searchinfo);
	}

	@Override
	public User getUserInfo(User user) {	//not used
		return this.get(user.getId());
	}

}
