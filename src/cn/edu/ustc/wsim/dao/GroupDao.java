package cn.edu.ustc.wsim.dao;

import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.util.page.Page;

public interface GroupDao extends BaseDao {
	
	public Group getGroupByNumber(Integer number);
	
	public boolean isGroupNumberExist(Integer number);
	
	//根据创建者，获取其创建的所有群
//	public List getGroupsByCreater(User creater);
	
	public List searchGroupByName(String name);

	public List<Group> listGroup(Page page);
	
//	public List getGroupsByUser

}
