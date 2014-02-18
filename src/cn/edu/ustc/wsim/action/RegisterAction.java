package cn.edu.ustc.wsim.action;

import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.enumerates.UserStatus;
import cn.edu.ustc.wsim.service.FriendGroupService;
import cn.edu.ustc.wsim.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 418574293840607848L;
	private String email;
	private String password;
	private String confpassword;
	private String name;
	
	private UserService userService;
	private FriendGroupService friendGroupService;
	
	private String errorMsg;
	
	@Override
	public String execute() {
		if(userService.getUserByEmail(email) != null)
		{
			errorMsg = "Email exist";
			return ERROR;
		} else {
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			user.setName(name);
			user.setStatus(UserStatus.ONLINE);
			
			if(userService.add(user)) {
				//注册成功后，添加默认分组
				friendGroupService.addDefaultFriendGroup(user);
				return SUCCESS;
			}
			else {
				errorMsg = "register error";
				return ERROR;
			}
				
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getConfpassword() {
		return confpassword;
	}

	public void setConfpassword(String confpassword) {
		this.confpassword = confpassword;
	}

	public FriendGroupService getFriendGroupService() {
		return friendGroupService;
	}

	public void setFriendGroupService(FriendGroupService friendGroupService) {
		this.friendGroupService = friendGroupService;
	}
	
	
	

}
