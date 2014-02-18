package cn.edu.ustc.wsim.service;

import java.util.List;
import java.util.Set;

import cn.edu.ustc.wsim.bean.Friend;
import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.bean.User;

public interface FriendService extends BaseService {
	
	public boolean addFriend(User requester, User receiver); 
	
	public boolean addFriend(User requester, User receiver, String friendGroupName, String remark);
	
	public boolean addFriend(User requester, User receiver, String friendGroupName);
	
	public boolean addFriend(String remark, User requester, User receiver);
	
	public List<Friend> getFriendsOfFriendGroup(FriendGroup friendGroup);
	
	public Friend getFriend(User self, User other);
	
	public List<Friend> searchFriend(String searchinfo);
	
	//获取某用户所有好友信息
	public Set<Friend> getFriendsOfUser(User user);
	
	//获取所有好友用户
	public Set<User> getFriendusersOfUser(User user);
	
}
