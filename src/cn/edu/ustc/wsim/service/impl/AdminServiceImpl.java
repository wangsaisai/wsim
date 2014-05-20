package cn.edu.ustc.wsim.service.impl;

import java.util.List;
import java.util.Map;

import cn.edu.ustc.wsim.bean.Admin;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.AdminDao;
import cn.edu.ustc.wsim.service.AdminService;
import cn.edu.ustc.wsim.util.page.Page;
import cn.edu.ustc.wsim.util.page.PageUtil;
import cn.edu.ustc.wsim.util.page.Result;

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

	@Override
	public Result listAdmin(Page page) {
		page = PageUtil.createPage(page, (int)adminDao.count());
		List<Admin> admins = adminDao.listAdmin(page);
		Result result = new Result();
		result.setPage(page);
		result.setList(admins);
		return result;
	}

}
