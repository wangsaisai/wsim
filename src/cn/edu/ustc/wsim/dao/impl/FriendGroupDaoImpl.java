package cn.edu.ustc.wsim.dao.impl;

import java.util.List;

import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.FriendGroupDao;

public class FriendGroupDaoImpl extends BaseDaoImpl implements FriendGroupDao {

	@Override
	public boolean del(int id) {
		return del(get(id));
	}
	
	@Override
	public FriendGroup get(int id) {
		return (FriendGroup) get("cn.edu.ustc.wsim.bean.FriendGroup", id);
	}

	@Override
	public long count() {
		String hsql = "select count(*) from FriendGroup";
		List count = super.getHibernateTemplate().find(hsql);
		return (Long) count.get(0);
	}

	@Override
	public List getAllFriendGroupOfUser(User user) {
		String hsql = "from FriendGroup where userId = " + user.getId();
//		if(super.getHibernateTemplate() == null)
//			System.out.println("aaaaaaaaa");
		return super.getHibernateTemplate().find(hsql);
	}

	@Override
	public FriendGroup getFriendGroupByName(User user, String name) {
		String hsql = "from FriendGroup where userId = " + user.getId() + " and name = '" + name + "'";
		List list = super.getHibernateTemplate().find(hsql);
		if(list.size() == 0)
			return null;
		else
			return (FriendGroup) list.get(0);
	}

	
	
}
