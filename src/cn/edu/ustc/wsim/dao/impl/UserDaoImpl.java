package cn.edu.ustc.wsim.dao.impl;

import java.util.List;

import cn.edu.ustc.wsim.bean.Admin;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.UserDao;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public boolean del(int id) {
		return del(get(id));
	}

	@Override
	public User get(int id) {
		return (User) get("cn.edu.ustc.wsim.bean.User", id);
	}
	
	@Override
	public long count() {
		String hsql = "select count(*) from User";
		List count = super.getHibernateTemplate().find(hsql);
		return (Long) count.get(0);
	}

	@Override
	public List<User> getUsersByName(String name) {
		String hsql = "from User where name = '" + name + "'";
		return super.getHibernateTemplate().find(hsql);
	}

	@Override
	public User getUserByEmail(String email) {
		String hsql = "from User where email = '" + email + "'";
		List list = super.getHibernateTemplate().find(hsql);
		if(list.size() == 0)
			return null;
		else
			return (User) list.get(0);
	}

	@Override
	public User getUserByEmailAndPassoord(String email, String password) {
		String hsql = "from User where email = '" + email + "' and password = '" + password + "'";
		List list = super.getHibernateTemplate().find(hsql);
		if(list.size() == 0)
			return null;
		else
			return (User) list.get(0);
	}

	@Override
	public List<User> searchUserByName(String name) {
		String hsql = " from User where name like '%" + name + "%'" ;
		return super.getHibernateTemplate().find(hsql);
	}


}
