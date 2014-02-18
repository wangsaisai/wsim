package cn.edu.ustc.wsim.dao.impl;

import java.util.List;

import cn.edu.ustc.wsim.bean.Friend;
import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.dao.FriendDao;

public class FriendDaoImpl extends BaseDaoImpl implements FriendDao {

	@Override
	public boolean del(int id) {
		return del(get(id));
	}
	
	@Override
	public Friend get(int id) {
		return (Friend) get("cn.edu.ustc.wsim.bean.Friend", id);
	}

	@Override
	public long count() {
		String hsql = "select count(*) from Friend";
		List count = super.getHibernateTemplate().find(hsql);
		return (Long) count.get(0);
	}

	@Override
	public List<Friend> getFriendsOfFriendGroup(FriendGroup friendGroup) {
		String hsql = "from Friend where friendGroupId = " + friendGroup.getId();
		return super.getHibernateTemplate().find(hsql);
	}

	
	
}
