package cn.edu.ustc.wsim.service;

import java.util.List;

import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.bean.User;

public interface FriendGroupService extends BaseService {
	
	public FriendGroup getFriendGroupByName(User user, String name);
	
	public boolean isNameExist(User user, String name);
	
	public List getAllFriendGroupOfUser(User user);
	
	public void addDefaultFriendGroup(User user);
	
	public boolean isFriend(User self, User other);
	
	public boolean isDefaultFriendGroup(FriendGroup friendGroup);

}
