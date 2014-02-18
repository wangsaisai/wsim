package cn.edu.ustc.wsim.dao.impl;

import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupUser;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.GroupUserDao;
import cn.edu.ustc.wsim.enumerates.GroupRole;

public class GroupUserDaoImpl extends BaseDaoImpl implements GroupUserDao {
	
	@Override
	public boolean del(int id) {
		return del(get(id));
	}
	
	@Override
	public GroupUser get(int id) {
		return (GroupUser) get("cn.edu.ustc.wsim.bean.GroupUser", id);
	}

	@Override
	public long count() {
		String hsql = "select count(*) from GroupUser";
		List count = super.getHibernateTemplate().find(hsql);
		return (Long) count.get(0);
	}


	@Override
	public GroupUser get(User user, Group group) {
		String hsql = "from GroupUser where userId = " + user.getId() + " and groupId = " + group.getId();
		List list = super.getHibernateTemplate().find(hsql);
		if(list.size() == 0)
			return null;
		else
			return (GroupUser) list.get(0);
	}


	@Override
	public List<GroupUser> getGroupUsersByUser(User user) {
		String hsql = "from GroupUser where userId = " + user.getId();
		return super.getHibernateTemplate().find(hsql);
	}

	@Override
	public List<GroupUser> getGroupUsersByGroup(Group group) {
		String hsql = "from GroupUser where groupId = " + group.getId();
		return super.getHibernateTemplate().find(hsql);
	}

	@Override
	public List<GroupUser> getGroupUsersByCreater(User creater) {
		String hsql = "from GroupUser where userId = " + creater.getId() + " and role = ?";
		return super.getHibernateTemplate().find(hsql, GroupRole.CREATER);
	}

	@Override
	public List<GroupUser> getGroupUsersByCreaterOrManager(User createrOrManager) {
		String hsql = "from GroupUser where userId = " + createrOrManager.getId() + " and (role = ? or role = ?)";
		GroupRole[] roles = new GroupRole[2];
		roles[0] = GroupRole.CREATER;
		roles[1] = GroupRole.MANAGER;
		return super.getHibernateTemplate().find(hsql, roles);
	}


}
