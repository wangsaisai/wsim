package cn.edu.ustc.wsim.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupRequest;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.GroupRequestDao;
import cn.edu.ustc.wsim.datastructure.OnlineUsers;
import cn.edu.ustc.wsim.service.GroupRequestService;
import cn.edu.ustc.wsim.service.GroupUserService;

public class GroupRequestServiceImpl extends BaseServiceImpl implements GroupRequestService {

	private GroupRequestDao groupRequestDao;
	private GroupUserService groupUserService;
	
	public GroupRequestDao getGroupRequestDao() {
		return groupRequestDao;
	}

	public void setGroupRequestDao(GroupRequestDao groupRequestDao) {
		this.groupRequestDao = groupRequestDao;
	}
	

	public GroupUserService getGroupUserService() {
		return groupUserService;
	}

	public void setGroupUserService(GroupUserService groupUserService) {
		this.groupUserService = groupUserService;
	}

	@Override
	public long count() {
		return groupRequestDao.count();
	}

	@Override
	public GroupRequest get(int id) {
		return (GroupRequest) groupRequestDao.get(id);
	}

	@Override
	public boolean del(int id) {
		return groupRequestDao.del(id);
	}
	
	@Override
	public Map<Group, List<GroupRequest>> getUndealGroupRequests(User createrOrManager) {
		List<Group> groups = groupUserService.getBeManagedGroups(createrOrManager);
//		List<GroupRequest> groupRequests = new ArrayList<>();
		HashMap<Group, List<GroupRequest>> maps = new HashMap<Group, List<GroupRequest>>();
		for (Group group : groups) {
			List<GroupRequest> groupRequests = groupRequestDao.getUndealGroupRequests(group);
			if( !(groupRequests == null || groupRequests.size() == 0) )
				maps.put(group, groupRequests);
		}
		return maps;
	}

	@Override
	public User getOnlineCreaterOrManager(Group group) {
		List<User> users = groupUserService.getCreaterAndManagerOfGroup(group);
		for (User user : users) {
			if(OnlineUsers.isLogin(user))
				return user;
		}
		return null;
	}

	@Override
	public GroupRequest get(User user, Group group) {
		return groupRequestDao.get(user, group);
	}



}
