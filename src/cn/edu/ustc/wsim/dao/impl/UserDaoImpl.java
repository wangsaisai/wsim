package cn.edu.ustc.wsim.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.UserDao;
import cn.edu.ustc.wsim.util.page.Page;

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

	@Override
	public List<User> listUser(Page page) {
		Query query = super.getSession().createQuery("from User order by id desc");
		// 设置每页显示多少个，设置多大结果。
		query.setMaxResults(page.getEveryPage());
		// 设置起点
		query.setFirstResult(page.getBeginIndex());
		return query.list();
	}


}
