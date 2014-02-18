package cn.edu.ustc.wsim.dao.impl;

import java.util.List;

import cn.edu.ustc.wsim.bean.Admin;
import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.GroupDao;

public class GroupDaoImpl extends BaseDaoImpl implements GroupDao {

	@Override
	public boolean del(int id) {
		return del(get(id));
	}

	@Override
	public Group get(int id) {
		return (Group) get("cn.edu.ustc.wsim.bean.Group", id);
	}
	
	@Override
	public long count() {
		String hsql = "select count(*) from Group";
		List count = super.getHibernateTemplate().find(hsql);
		return (Long) count.get(0);
	}

	@Override
	public Group getGroupByNumber(Integer number) {
		String hsql = "from Group where number = " + number;
		List list = super.getHibernateTemplate().find(hsql);
		if(list.size() == 0)
			return null;
		else
			return (Group) list.get(0);
	}

	@Override
	public boolean isGroupNumberExist(Integer number) {
		String hsql = "select count(*) from Group where number = " + number;
		List count = super.getHibernateTemplate().find(hsql);
		if((Long)count.get(0) == 0)
			return false;
		else
			return true;
	}

//	@Override
//	public List getGroupsByCreater(User creater) {
//		String hsql = "from Group where creater = " + creater.getId();
//		return super.getHibernateTemplate().find(hsql);
//	}

	@Override
	public List searchGroupByName(String name) {
		String hsql = "from Group where name like '%" + name + "%'";
		return super.getHibernateTemplate().find(hsql);
	}

	
	
}
