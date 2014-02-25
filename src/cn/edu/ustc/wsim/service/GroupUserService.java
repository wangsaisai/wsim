package cn.edu.ustc.wsim.service;

import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupUser;
import cn.edu.ustc.wsim.bean.User;

public interface GroupUserService extends BaseService {
	
	//获取某用户的所有群组信息
	public List<Group> getGroupsByUser(User user);
	
	//获取收到该用户管理的群
	public List<Group> getBeManagedGroups(User user);
	
	//获取某群组的所有用户信息
	public List<User> getUsersByGroup(Group group);
	
	public List<GroupUser> getGroupUsersByUser(User user);
	
	public List<GroupUser> getGroupUsersByGroup(Group group);
	
	//退出某群
	public boolean quitGroup(User user, Group group);
	
	//用户是否在某群众
	public boolean isBelong(User user, Group group);
	
	public GroupUser get(User user, Group group);
	
	//判断当前登录用户是否是群主
	public boolean isCreater(User user, Group group);
	
	public List<User> getCreaterAndManagerOfGroup(Group group);

}
