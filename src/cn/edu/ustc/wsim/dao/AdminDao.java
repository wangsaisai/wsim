package cn.edu.ustc.wsim.dao;

import java.util.List;

import cn.edu.ustc.wsim.bean.Admin;
import cn.edu.ustc.wsim.util.page.Page;

public interface AdminDao extends BaseDao {
	
	//根据email和密码获取对象
	public Admin getAdminByEmailAndPassword(String email, String password);
	
	//用于判断此email地址是否存在
	public Admin getAdminByEmail(String email);
	
	public List<Admin> listAdmin(Page page);

}
