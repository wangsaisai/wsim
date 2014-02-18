package cn.edu.ustc.wsim.service;

import java.util.HashMap;
import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupRequest;
import cn.edu.ustc.wsim.bean.User;

public interface GroupRequestService extends BaseService {
	
	public HashMap<Group, List<GroupRequest>> getUndealGroupRequests(User createrOrManager);
	
}
