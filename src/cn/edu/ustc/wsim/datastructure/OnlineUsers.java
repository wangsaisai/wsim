package cn.edu.ustc.wsim.datastructure;

import java.util.HashMap;
import java.util.Map;

import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.enumerates.UserStatus;

public class OnlineUsers {
	
	private static final Map<Integer, User> users = new HashMap<Integer, User>();
	
	public static void addUser(User user) {
		Integer id = user.getId();
		if(users.containsKey(id))
			users.remove(id);
		
		users.put(id, user);
	}
	
	public static void removeUser(Integer userId) {
		if(users.containsKey(userId))
			users.remove(userId);
	}
	
	public static void updateUserStatus(Integer userId, UserStatus status) {
		if(users.containsKey(userId))
			users.get(userId).setStatus(status);
	}
	
	public static Integer countOnlineUsers() {
		return users.size();
	}
	
	public static boolean isLogin(Integer userId) {
		return users.containsKey(userId);
	}
	
	public static boolean isLogin(User user) {
		return users.containsValue(user);
	}

	public static Map<Integer, User> getUsers() {
		return users;
	}
	
	public static User getUser(Integer userId) {
		if(users.containsKey(userId))
			return users.get(userId);
		else
			return null;
	}
	
	
	
	
	
	//////////////////////////////
	public static void show() {
		System.out.println("在线用户数： " + countOnlineUsers());
	}
	

}
