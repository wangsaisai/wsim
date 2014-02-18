package cn.edu.ustc.wsim.dao;

import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupUser;
import cn.edu.ustc.wsim.bean.User;

public interface GroupUserDao extends BaseDao {
	
	//获取某用户的所有群组信息
//	public List<Group> getGroupsByUser(User user);
	
	//获取某群组的所有用户信息
//	public List<User> getUsersByGroup(Group group);
	
	public List<GroupUser> getGroupUsersByUser(User user);
	
	public List<GroupUser> getGroupUsersByGroup(Group group);
	
	public List<GroupUser> getGroupUsersByCreater(User creater);
	
	public GroupUser get(User user, Group group);
	
	public List<GroupUser> getGroupUsersByCreaterOrManager(User createrOrManager);
	
//	public List<Group> getGroupsByCreater(User creater);

}
