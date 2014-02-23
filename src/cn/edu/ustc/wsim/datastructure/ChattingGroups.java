package cn.edu.ustc.wsim.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupUser;
import cn.edu.ustc.wsim.service.GroupService;
import cn.edu.ustc.wsim.service.GroupUserService;

public class ChattingGroups {
	
	
	private GroupService groupService;
	private GroupUserService groupUserService;
	
	public GroupUserService getGroupUserService() {
		return groupUserService;
	}

	public void setGroupUserService(GroupUserService groupUserService) {
		this.groupUserService = groupUserService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	
	
	private static final Map<Integer, Group> groups = new HashMap<Integer, Group>();
	



	public static Map getGroups() {
		return groups;
	}
	
	
	public static Group getGroup(Integer groupId) {
		if(groups.containsKey(groupId))
			return groups.get(groupId);
		else
			return null;
	}
	
	
	public static Integer count() {
		return groups.size();
	}
	
	
	public static boolean isChatting(Integer groupId) {
		if(groups.containsKey(groupId))
			return true;
		else
			return false;
	}
	
	
	/*
	 * 当群通话窗口被打开时，将该群加入groups中
	 */
	public void addGroup(Integer groupId) {
		//若已加入，则退出
		if(isChatting(groupId))
			return;
		
		Group group = (Group) groupService.get(groupId);
		group.setGroupmessages(new HashSet<>());
		group.setGrouprequests(new HashSet<>());
		Set<GroupUser> gus = new HashSet<>();
		
		//从OnlineUsers中查找，得到该群中所有的在线用户
		for (GroupUser gu : groupUserService.getGroupUsersByGroup(group)) {
			if(OnlineUsers.isLogin(gu.getUser()))
				gus.add(gu);
		}
		group.setGroupUsers(gus);
	}
	
	
	//groupUser登录时
	public void addUserToGroup(Integer userId, Integer groupId) {
		GroupUser gu = new GroupUser();
		gu.setUser(OnlineUsers.getUser(userId));
		Group group = getGroup(groupId);
		if(group != null)
			group.getGroupUsers().add(gu);
		else {
			this.addGroup(groupId);
			this.addUserToGroup(userId, groupId);
		}
	}
	
	
	/*
	 * 用户退出，应获取其所有的群，然后多次执行此方法
	 */
	public void rmUserFromGroup(Integer userId, Integer groupId) {
		GroupUser gu = new GroupUser();
		gu.setUser(OnlineUsers.getUser(userId));
		Group group = getGroup(groupId);
		if(group!=null) {
			group.getGroupUsers().remove(gu);
			//群中无人时
			/*
			 * 存在问题，当所有群窗口都关闭时，也应该清除group
			 */
			if(group.getGroupUsers().size() == 0) {
				groups.remove(groupId);
			}
				
		}
	}
	
	
	
	
	
	
	//////////////////////////////
	public static void show() {
		System.out.println("在线群数： " + count());
	}
	
	
	
	
	

}
