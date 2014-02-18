package cn.edu.ustc.wsim.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.datastructure.OnlineUsers;

import com.opensymphony.xwork2.ActionSupport;

public class DisplayOnlineUsers extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5986556663155818798L;
	private Set<User> onlineUsers;
	
	
	@Override
	public String execute() {
		Map map = OnlineUsers.getUsers();
		Iterator iter = map.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry)iter.next();
			User user = (User) entry.getValue();
			onlineUsers.add(user);
		}
		return SUCCESS;
	}


	public Set<User> getOnlineUsers() {
		return onlineUsers;
	}


	public void setOnlineUsers(Set<User> onlineUsers) {
		this.onlineUsers = onlineUsers;
	}
	
	

	
	

}
