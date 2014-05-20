package cn.edu.ustc.wsim.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.edu.ustc.wsim.bean.Admin;
import cn.edu.ustc.wsim.dao.AdminDao;
import cn.edu.ustc.wsim.util.page.Page;

public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {

	@Override
	public boolean del(int id) {
		return del(get(id));
	}

	@Override
	public long count() {
		String hsql = "select count(*) from Admin";
		List count = super.getHibernateTemplate().find(hsql);
		return (Long) count.get(0);
	}
	
	@Override
	public Admin get(int id) {
		return (Admin) get("cn.edu.ustc.wsim.bean.Admin", id);
	}

	@Override
	public Admin getAdminByEmailAndPassword(String email, String password) {
		String hsql = "from Admin where email = '" + email + "' and password = '" + password + "'";
		List list = super.getHibernateTemplate().find(hsql);
		if(list.size() == 0)
			return null;
		else
			return (Admin) list.get(0);
	}

	@Override
	public Admin getAdminByEmail(String email) {
		String hsql = "from Admin where email = '" + email + "'";
		List list = super.getHibernateTemplate().find(hsql);
		if(list.size() == 0)
			return null;
		else
			return (Admin) list.get(0);
	}

	@Override
	public List<Admin> listAdmin(Page page) {
		Query query = super.getSession().createQuery("from Admin order by id desc");
		// 设置每页显示多少个，设置多大结果。
		query.setMaxResults(page.getEveryPage());
		// 设置起点
		query.setFirstResult(page.getBeginIndex());
		return query.list();
	}

}
