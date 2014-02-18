package cn.edu.ustc.wsim.service.impl;

import java.util.Map;

import cn.edu.ustc.wsim.bean.Admin;
import cn.edu.ustc.wsim.dao.AdminDao;
import cn.edu.ustc.wsim.service.AdminService;

import com.opensymphony.xwork2.ActionContext;

public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

	private AdminDao adminDao;
	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Admin getAdminByEmailAndPassword(String email, String password) {
		return adminDao.getAdminByEmailAndPassword(email, password);
	}

	@Override
	public Admin getAdminByEmail(String email) {
		return adminDao.getAdminByEmail(email);
	}
	
	@Override
	public long count() {
		return adminDao.count();
	}

	@Override
	public Admin get(int id) {
		return (Admin) adminDao.get(id);
	}
	
	@Override
	public boolean del(int id) {
		return adminDao.del(id);
	}

	@Override
	public Admin getLoginAdmin() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Admin admin = (Admin)session.get("admin");
		return admin;
	}

}
