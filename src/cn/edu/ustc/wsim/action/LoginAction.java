package cn.edu.ustc.wsim.action;

import java.util.Map;

import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.datastructure.OnlineUsers;
import cn.edu.ustc.wsim.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1032365417923582757L;
	private String email;
	private String password;
	
	private UserService userService;
	
	private String errorMsg;
	
	private User user;
	
	@Override
	public String execute() {
		
		//若用户已登录，…………
		
		user = userService.getUserByEmail(email);
		if(user == null) {
			errorMsg = "email地址不存在，请重新输入";
			return ERROR;
		} else if(!user.getPassword().equals(password)) {
//			System.out.println(user.getPassword());
//			System.out.println(password);
			errorMsg = "email或密码错误，请重新输入";
			return ERROR;
		}
		else {
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("user", user);
			OnlineUsers.addUser(user);
//			onlineUsers.test();
			return SUCCESS;
		}
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
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
