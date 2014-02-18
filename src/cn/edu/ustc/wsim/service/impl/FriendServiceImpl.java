package cn.edu.ustc.wsim.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.edu.ustc.wsim.bean.Friend;
import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.FriendDao;
import cn.edu.ustc.wsim.datastructure.GlobalFinal;
import cn.edu.ustc.wsim.service.FriendGroupService;
import cn.edu.ustc.wsim.service.FriendService;
import cn.edu.ustc.wsim.service.UserService;
import cn.edu.ustc.wsim.util.CheckEmail;

public class FriendServiceImpl extends BaseServiceImpl implements FriendService {

	private FriendDao friendDao;
	private FriendGroupService friendGroupService;
	private UserService userService;
	
	
	public FriendDao getFriendDao() {
		return friendDao;
	}

	public void setFriendDao(FriendDao friendDao) {
		this.friendDao = friendDao;
	}
	
	public FriendGroupService getFriendGroupService() {
		return friendGroupService;
	}

	public void setFriendGroupService(FriendGroupService friendGroupService) {
		this.friendGroupService = friendGroupService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public long count() {
		return friendDao.count();
	}

	@Override
	public Friend get(int id) {
		return (Friend) friendDao.get(id);
	}
	
	@Override
	public boolean del(int id) {
		return friendDao.del(id);
	}

	@Override
	public boolean addFriend(User requester, User receiver, String friendGroupName, String remark) {
		
		FriendGroup fg = null;
		if(friendGroupName == null || "".equals(friendGroupName)) {
			fg = friendGroupService.getFriendGroupByName(requester, GlobalFinal.getDefaultFriendGroupName()); 
		}
		else {
			fg = friendGroupService.getFriendGroupByName(requester, friendGroupName);
			if(fg == null)
				fg = friendGroupService.getFriendGroupByName(requester, GlobalFinal.getDefaultFriendGroupName()); 
		}
		
		Friend f = new Friend();
		f.setFriendgroup(fg);
		f.setUser(receiver);
		f.setRemark(remark);
		
		return friendDao.add(f);
	}

	@Override
	public boolean addFriend(User requester, User receiver) {
		return this.addFriend(requester, receiver, null, null);
	}

	@Override
	public boolean addFriend(User requester, User receiver, String friendGroupName) {
		return this.addFriend(requester, receiver, friendGroupName, null);
	}

	@Override
	public boolean addFriend(String remark, User requester, User receiver) {
		return this.addFriend(requester, receiver, null, remark);
	}

	@Override
	public List<Friend> getFriendsOfFriendGroup(FriendGroup friendGroup) {
		return friendDao.getFriendsOfFriendGroup(friendGroup);
	}

	@Override
	public Friend getFriend(User self, User other) {
		List<FriendGroup> friendGroups = friendGroupService.getAllFriendGroupOfUser(self);
		for (FriendGroup friendGroup : friendGroups) {
			List<Friend> friends = this.getFriendsOfFriendGroup(friendGroup);
			for (Friend friend : friends) {
				if(friend.getUser().equals(other))
					return friend;
			}
		}
		return null;
	}

	@Override
	public List<Friend> searchFriend(String searchinfo) {
		List<Friend> friends = new ArrayList<Friend>();
		
		CheckEmail ce = new CheckEmail();
		if(ce.isEmail(searchinfo)) {	//email搜索
			User user = userService.getUserByEmail(searchinfo);
			Friend friend = this.getFriend(getLoginUser(), user);
			if(friend != null)
				friends.add(friend);
			
		} else {		//字符串搜索
			List<FriendGroup> friendGroups = friendGroupService.getAllFriendGroupOfUser(getLoginUser());
			for (FriendGroup friendGroup : friendGroups) {
				List<Friend> friendss = this.getFriendsOfFriendGroup(friendGroup);
				for (Friend friend : friendss) {
					//如果好友的name 字符串中包含searchinfo这个子串
					if(friend.getUser().getName().contains(searchinfo))
						friends.add(friend);
				}
			}
		}
		
		return friends;
	}

	@Override
	public Set<Friend> getFriendsOfUser(User user) {
		Set<Friend> friends = new HashSet<Friend>(0);
		
		List<FriendGroup> friendGroups = friendGroupService.getAllFriendGroupOfUser(user);
		for (FriendGroup friendGroup : friendGroups) {
			friends.addAll(this.getFriendsOfFriendGroup(friendGroup));
		}
		return friends;
	}
	
	@Override
	public Set<User> getFriendusersOfUser(User user) {
		Set<User> users = new HashSet<User>(0);
		
		Set<Friend> friends = this.getFriendsOfUser(user);
		for(Iterator iterator = friends.iterator(); iterator.hasNext();) {
			Friend friend = (Friend) iterator.next();
			users.add(friend.getUser());
		}
		
		return users;
	}



}
