package cn.edu.ustc.wsim.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.datastructure.ChattingGroups;
import cn.edu.ustc.wsim.datastructure.OnlineUsers;
import cn.edu.ustc.wsim.service.GroupUserService;
import cn.edu.ustc.wsim.service.UserService;
import cn.edu.ustc.wsim.util.MD6;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAjaxAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1032365417923582757L;
	private String email;
	private String password;
	
	private UserService userService;
	
	private GroupUserService groupUserService;
	
	private User user;
	
	private String result;
	
	@Override
	public String execute() {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		user = userService.getUserByEmail(email);
		if(user == null) {
			map.put("error", false);
			map.put("msg", "email地址不存在");
		} else if(!user.getPassword().equals(password)) {
			map.put("error", false);
			map.put("msg", "email或密码错误");
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
			
			map.put("success", true);
			map.put("userId", user.getId());
			map.put("name", user.getName());
			map.put("msg","ok");
		}
		
		JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
		result = json.toString();//给result赋值，传递给页面
		System.out.println(result);
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
		//密码密文加密,将用户的密码加上后缀
		this.password = md6.getMD5ofStr(password + UserAction.tail);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
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

	public GroupUserService getGroupUserService() {
		return groupUserService;
	}

	public void setGroupUserService(GroupUserService groupUserService) {
		this.groupUserService = groupUserService;
	}

}
