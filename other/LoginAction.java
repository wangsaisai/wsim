package cn.edu.ustc.wsim.action;

import java.util.List;
import java.util.Map;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.datastructure.ChattingGroups;
import cn.edu.ustc.wsim.datastructure.OnlineUsers;
import cn.edu.ustc.wsim.service.GroupUserService;
import cn.edu.ustc.wsim.service.UserService;
import cn.edu.ustc.wsim.util.MD6;

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
	
	private GroupUserService groupUserService;
	
	private String errorMsg;
	
	private User user;
	
	@Override
	public String execute() {
		
		user = userService.getUserByEmail(email);
		if(user == null) {
			errorMsg = "email地址不存在";
			return ERROR;
		} else if(!user.getPassword().equals(password)) {
			errorMsg = "email或密码错误";
			return ERROR;
		}
		else {
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("user", user);
			
			/*
			 * 用户登录后，将user存入相应数据结构OnlineUsers，  ChattingGroups
			 */
			//保存登录用户至数据结构OnlineUsers中，	记录当前用户在线信息
			OnlineUsers.addUser(user);
			//将用户加入ChattingGroups数据结构中
			List<Group> groups = groupUserService.getGroupsByUser(user);
			for (Group group : groups) {
				ChattingGroups.addUser(group.getId(), user);
			}
			
		}
		
		return SUCCESS;
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
		//密码密文加密,将用户的密码加上后缀'zfjfy'后在用md5加密算法加密
		this.password = md6.getMD5ofStr(password + "zfjfy");
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

	public GroupUserService getGroupUserService() {
		return groupUserService;
	}

	public void setGroupUserService(GroupUserService groupUserService) {
		this.groupUserService = groupUserService;
	}

}
