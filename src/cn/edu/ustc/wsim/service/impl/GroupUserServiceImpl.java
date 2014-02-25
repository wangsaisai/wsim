package cn.edu.ustc.wsim.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupUser;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.GroupUserDao;
import cn.edu.ustc.wsim.service.GroupUserService;

public class GroupUserServiceImpl extends BaseServiceImpl implements GroupUserService {

	private GroupUserDao groupUserDao;
	
	public GroupUserDao getGroupUserDao() {
		return groupUserDao;
	}

	public void setGroupUserDao(GroupUserDao groupUserDao) {
		this.groupUserDao = groupUserDao;
	}
	
	
	//从List<GroupUser>中提取出List<Group>
	private List<Group> getGroupsByGroupUsers(List<GroupUser> groupUsers) {
		List<Group> groups = new ArrayList<Group>();
		for (GroupUser groupUser : groupUsers) {
			groups.add(groupUser.getGroup());
		}
		return groups;
	}
	
	//从List<GroupUser>中提取出List<User>
	private List<User> getUserByGroupUsers(List<GroupUser> groupUsers) {
		List<User> users = new ArrayList<User>();
		for (GroupUser groupUser : groupUsers) {
			users.add(groupUser.getUser());
		}
		return users;
	}
	
	

	@Override
	public long count() {
		return groupUserDao.count();
	}

	@Override
	public GroupUser get(int id) {
		return (GroupUser) groupUserDao.get(id);
	}
	
	@Override
	public boolean del(int id) {
		return groupUserDao.del(id);
	}

	@Override
	public List<Group> getGroupsByUser(User user) {
		return this.getGroupsByGroupUsers(this.getGroupUsersByUser(user));
	}

	@Override
	public List<User> getUsersByGroup(Group group) {
		return this.getUserByGroupUsers(this.getGroupUsersByGroup(group));
	}

	@Override
	public boolean quitGroup(User user, Group group) {
		GroupUser groupUser = groupUserDao.get(user, group);
		if(groupUser != null)
			return groupUserDao.del(groupUser);
		else
			return false;
	}
	
	@Override
	public boolean isBelong(User user, Group group) {
		if(this.get(user, group) == null)
			return false;
		else
			return true;
	}

	@Override
	public GroupUser get(User user, Group group) {
		return groupUserDao.get(user, group);
	}

	@Override
	public List<GroupUser> getGroupUsersByUser(User user) {
		return groupUserDao.getGroupUsersByUser(user);
	}

	@Override
	public List<GroupUser> getGroupUsersByGroup(Group group) {
		return groupUserDao.getGroupUsersByGroup(group);
	}

	@Override
	public List<Group> getBeManagedGroups(User user) {
		return this.getGroupsByGroupUsers(groupUserDao.getGroupUsersByCreaterOrManager(user));
	}

	@Override
	public boolean isCreater(User user, Group group) {
		if(groupUserDao.get(user, group) == null)
			return false;
		else
			return true;
	}

	@Override
	public List<User> getCreaterAndManagerOfGroup(Group group) {
		List<GroupUser> gus = groupUserDao.getGroupUsersByGroup(group);
		List<User> users = new ArrayList<User>();
		for (GroupUser gu : gus) {
			users.add(gu.getUser());
		}
		return users;
	}

}
