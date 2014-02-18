package cn.edu.ustc.wsim.service;

import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.enumerates.UserRelation;

public interface UserService extends BaseService {
	
	public List<User> getUsersByName(String name);
	
	public User getUserByEmail(String email);
	
	public User getUserByEmailAndPassoord(String email, String password);
	
	public boolean isLogin(User user);
	
	
	//判断user2, 是否为user1 的好友
	public boolean isFriend(User self, User other);
	
	
	public UserRelation getRelation(User self, User other);
	
	public List<User> searchUser(String searchinfo);
	
	public User getUserInfo(User user);
	
	
//	//通知当前在线用户，有好友请求信息。
//	public void sendFriendRequestMessage(User user);
//	
//	public void sendGroupRequestMessage(User user);
//	
//	public void sendFriendMessageComingMessage(User user, User requester);
//	
//	public void sendGroupMessageComingMessage(User user, Group group);
//	
//	public void sendAcceptGroupRequestMessage(User user, Group group);
//	
//	public void sendAcceptFriendRequestMessage(User user, User friend);
}
