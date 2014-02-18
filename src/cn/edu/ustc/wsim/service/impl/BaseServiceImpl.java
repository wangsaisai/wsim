package cn.edu.ustc.wsim.service.impl;

import java.util.List;
import java.util.Map;

import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.BaseDao;
import cn.edu.ustc.wsim.service.BaseService;

import com.opensymphony.xwork2.ActionContext;

public class BaseServiceImpl implements BaseService {

	private BaseDao dao;
	
	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(Object C) {
		return dao.add(C);
	}

	@Override
	public boolean del(Object C) {
		return dao.del(C);
	}

	@Override
	public boolean update(Object C) {
		return dao.update(C);
	}

	@Override
	public Object get(String cls, int objId) {
		return dao.get(cls, objId);
	}

	@Override
	public List get(String sql) {
		return dao.get(sql);
	}
	
	@Override
	public boolean del(int id) {
		return dao.del(id);
	}
	
	public User getLoginUser() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get("user");
		return user;
	}

	
	//子类会重写一下方法
	
	@Override
	public long count() {
		return 0;
	}

	@Override
	public Object get(int id) {
		return null;
	}

}
