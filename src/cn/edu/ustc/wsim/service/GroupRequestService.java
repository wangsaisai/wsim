package cn.edu.ustc.wsim.service;

import java.util.List;
import java.util.Map;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupRequest;
import cn.edu.ustc.wsim.bean.User;

public interface GroupRequestService extends BaseService {
	
	public Map<Group, List<GroupRequest>> getUndealGroupRequests(User createrOrManager);

	public User getOnlineCreaterOrManager(Group group);

	public GroupRequest get(User user, Group group);
	
}
