package cn.edu.ustc.wsim.dao;

import java.util.List;

public interface BaseDao {
	
	public boolean add(Object C);
	
	//删除某数据
	public boolean del(Object C);
	
	//根据id删除数据
	public boolean del(int id);
	
	public boolean update(Object C);
	
	//根据objId，获取对象
	public Object get(String cls, int objId);
	
	public Object get(int id);
	
	//根据sql语句获取对象
	public List get(String sql);
	
	public long count();

}
