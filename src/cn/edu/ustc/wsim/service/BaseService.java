package cn.edu.ustc.wsim.service;

import java.util.List;

import cn.edu.ustc.wsim.bean.User;

public interface BaseService {
	
	public boolean add(Object C);
	
	//删除某数据
	public boolean del(Object C);
	
	//根据id删除数据
	public boolean del(int id);
	
	public boolean update(Object C);
	
	//根据objId，获取对象
	public Object get(String cls, int objId);
	
	//根据sql语句获取对象
	public List get(String sql);
	
	public long count();
	
	public Object get(int id);
	
	
	//获取当前登录用户
	public User getLoginUser();

}
