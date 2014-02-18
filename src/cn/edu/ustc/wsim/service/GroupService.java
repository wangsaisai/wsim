package cn.edu.ustc.wsim.service;

import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.User;

public interface GroupService extends BaseService {
	
	public Group getGroupByNumber(Integer number);
	
	public Integer genGroupNumber();

	public boolean isGroupNumberExist(Integer number);
	
	public boolean createrGroup(Group group);
	
	//判断当前登录用户是否是群主
	public boolean isCreater(User user, Group group);
	
	public List<Group> searchGroup(String searchinfo);


//	public boolean isGroupUserOfGroup(User user, Group group);
	
}
