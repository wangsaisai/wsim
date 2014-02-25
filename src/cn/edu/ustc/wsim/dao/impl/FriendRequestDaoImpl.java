package cn.edu.ustc.wsim.dao.impl;

import java.util.List;

import cn.edu.ustc.wsim.bean.FriendRequest;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.FriendRequestDao;
import cn.edu.ustc.wsim.enumerates.FriendRequestResult;

public class FriendRequestDaoImpl extends BaseDaoImpl implements FriendRequestDao {

	@Override
	public boolean del(int id) {
		return del(get(id));
	}
	
	@Override
	public FriendRequest get(int id) {
		return (FriendRequest) get("cn.edu.ustc.wsim.bean.FriendRequest", id);
	}

	@Override
	public long count() {
		String hsql = "select count(*) from FriendRequest";
		List count = super.getHibernateTemplate().find(hsql);
		return (Long) count.get(0);
	}

	@Override
	public List<FriendRequest> getUndealFriendRequests(User user) {
		//QQQQQQQQQQQQ
		String hsql = "from FriendRequest where responder = " + user.getId() + " and result = ?";
		
		return super.getHibernateTemplate().find(hsql, FriendRequestResult.UNDEAL);
	}

	@Override
	public FriendRequest get(User requester, User responder) {
		String hsql = "from FriendRequest where requester = " + requester.getId() + " and responder = " + responder.getId();
		List<FriendRequest> frs = super.getHibernateTemplate().find(hsql);
		return frs.get(frs.size() - 1);
	}


}
