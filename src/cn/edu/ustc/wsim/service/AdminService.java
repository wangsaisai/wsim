package cn.edu.ustc.wsim.service;

import cn.edu.ustc.wsim.bean.Admin;
import cn.edu.ustc.wsim.bean.User;

public interface AdminService extends BaseService {
	
	//根据email和密码获取对象
	public Admin getAdminByEmailAndPassword(String email, String password);
	
	//用于判断此email地址是否存在
	public Admin getAdminByEmail(String email);
	
	
	public Admin getLoginAdmin();
	
}
