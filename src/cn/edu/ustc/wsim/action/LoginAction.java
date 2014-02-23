package cn.edu.ustc.wsim.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

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
	
	private String result;
	
	@Override
	public String execute() {
		
		//若用户已登录，…………
		
		user = userService.getUserByEmail(email);
		if(user == null) {
			result = "{\"result\":\"success\",\"errorMsg\":\"email地址不存在\"}";
		} else if(!user.getPassword().equals(password)) {
			result = "{\"result\":\"error\",\"errorMsg\":\"email或密码错误\"}";
		}
		else {
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("user", user);
			OnlineUsers.addUser(user);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("success", true);
			map.put("msg","ok");
			
			JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
			result = json.toString();//给result赋值，传递给页面
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
