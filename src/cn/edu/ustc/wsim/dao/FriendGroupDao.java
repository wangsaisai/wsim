package cn.edu.ustc.wsim.dao;

import java.util.List;

import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.bean.User;

public interface FriendGroupDao extends BaseDao {
	
	public FriendGroup getFriendGroupByName(User user, String name);
	
	public List getAllFriendGroupOfUser(User user);

}
