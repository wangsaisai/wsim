package cn.edu.ustc.wsim.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.ustc.wsim.dao.BaseDao;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

	@Override
	public boolean add(Object C) {
		super.getHibernateTemplate().save(C);
		return true;
	}

	@Override
	public boolean del(Object C) {
		super.getHibernateTemplate().delete(C);
		return true;
	}

	@Override
	public boolean update(Object C) {
		super.getHibernateTemplate().update(C);
		return true;	
	}

	@Override
	public Object get(String cls, int objId) {
		return super.getHibernateTemplate().get(cls, objId);
	}

	@Override
	public List get(String sql) {
		return super.getHibernateTemplate().find(sql);
	}

	@Override
	public boolean del(int id) {
		return false;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public Object get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
