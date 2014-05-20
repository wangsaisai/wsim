package cn.edu.ustc.wsim.action;

import java.util.List;
import java.util.Map;

import cn.edu.ustc.wsim.bean.Admin;
import cn.edu.ustc.wsim.service.AdminService;
import cn.edu.ustc.wsim.service.UserService;
import cn.edu.ustc.wsim.util.MD6;
import cn.edu.ustc.wsim.util.page.Page;
import cn.edu.ustc.wsim.util.page.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class AdminAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8716505344511539938L;
	private Integer id;
	private String email;
	private String password;
	private String confpassword;
	private String oldPassword;
	
	private List admins;
	
	private AdminService adminService;
	
	private UserService userService;
	
	private String errorMsg;
	
	private int currentPage;
	private Page page;
	
	
	//用于密码加密，在用户输入的密码后添加统一后缀
	private final String tail = "admin_tail";
	
	
	@InputConfig(resultName="adminLoginInputError")
	public String adminLogin() {
		Admin admin = adminService.getAdminByEmailAndPassword(email, password);
		if(admin == null) {
			errorMsg = "登录失败，请重试";
			return "adminLoginError";
		} else {
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("admin", admin);
			return "adminLoginSuccess";
		}
	}
	
	
	public String adminLogout() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("admin");
		return "adminLogout";
	}
	

	@InputConfig(resultName="addAdminInputError")
	public String addAdmin() {
		if(adminService.getAdminByEmail(email) != null)
		{
			errorMsg = "Email exist";
			return "addError";
		} else {
			Admin admin = new Admin();
			admin.setEmail(email);
			admin.setPassword(password);
			
			if(adminService.add(admin))
				return "addSuccess";
			else {
				errorMsg = "add Admin error";
				return "addError";
			}
				
		}
	}
	
	public String delAdmin() {
		int intId = id;
		if(adminService.del(intId))
			return "delSuccess";
		else {
			errorMsg = "del error";
			return "delError";
		}
	}
	
	@InputConfig(resultName="changePasswordInputError")
	public String changePassword() {
		int id = adminService.getLoginAdmin().getId();
		Admin admin = (Admin) adminService.get(id);

		if(!oldPassword.equals(admin.getPassword())) {
			errorMsg = "old password error";
			return "changePasswordError";
		}
		
		admin.setPassword(password);
		if(adminService.update(admin)) {
			return "changePasswordSuccess";
		} else {
			errorMsg = "change password error";
			return "changePasswordError";
		}
	}
	
	
//	public String listAdmin() {
//		this.admins = adminService.get("from Admin");
//		return "list";
//	}
	
	
	public String listAdmin() {
		page = this.pageInfo();
		Result result = adminService.listAdmin(page);
		page = result.getPage();
		admins = result.getList();
		return "list";
	}
	
	
	
	private Page pageInfo(){
		Page page = new Page();
		page.setEveryPage(10);
		page.setCurrentPage(currentPage);
		return page;
	}
	
	
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage <= 0)
			this.currentPage = 1;
		else
			this.currentPage = currentPage;
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		MD6 md6 = new MD6();
		//密码密文加密,将用户的密码加上后缀
		this.password = md6.getMD5ofStr(password + tail);
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public String getConfpassword() {
		return confpassword;
	}


	public void setConfpassword(String confpassword) {
		MD6 md6 = new MD6();
		//密码密文加密,将用户的密码加上后缀
		this.confpassword = md6.getMD5ofStr(confpassword + tail);
	}


	public List getAdmins() {
		return admins;
	}


	public void setAdmins(List admins) {
		this.admins = admins;
	}


	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		MD6 md6 = new MD6();
		//密码密文加密,将用户的密码加上后缀
		this.oldPassword = md6.getMD5ofStr(oldPassword + tail);
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
