package cn.edu.ustc.wsim.dao;

import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupRequest;
import cn.edu.ustc.wsim.bean.User;


public interface GroupRequestDao extends BaseDao {
	
	public List<GroupRequest> getUndealGroupRequests(Group group);

	public GroupRequest get(User user, Group group);
}
