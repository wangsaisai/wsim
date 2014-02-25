package cn.edu.ustc.wsim.dao.impl;

import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupRequest;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.GroupRequestDao;
import cn.edu.ustc.wsim.enumerates.GroupRequestResult;

public class GroupRequestDaoImpl extends BaseDaoImpl implements GroupRequestDao {

	@Override
	public boolean del(int id) {
		return del(get(id));
	}
	
	@Override
	public GroupRequest get(int id) {
		return (GroupRequest) get("cn.edu.ustc.wsim.bean.GroupRequest", id);
	}

	@Override
	public long count() {
		String hsql = "select count(*) from GroupRequest";
		List count = super.getHibernateTemplate().find(hsql);
		return (Long) count.get(0);
	}

	@Override
	public List<GroupRequest> getUndealGroupRequests(Group group) {
		//QQQQQQQQQQQQ
		String hsql = "from GroupRequest where groupId = " + group.getId() + " and result = ?";
		
		return super.getHibernateTemplate().find(hsql, GroupRequestResult.UNDEAL);
		//find(hsql,enum);
	}

	@Override
	public GroupRequest get(User user, Group group) {
		String hsql = "from GroupRequest where userId = " + user.getId() + " and groupId = " + group.getId();
		List<GroupRequest> grs = super.getHibernateTemplate().find(hsql);
		return grs.get(grs.size() - 1);
	}


}
