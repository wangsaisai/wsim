package cn.edu.ustc.wsim.action;

import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.datastructure.OnlineUsers;
import cn.edu.ustc.wsim.service.AdminService;
import cn.edu.ustc.wsim.service.GroupMessageService;
import cn.edu.ustc.wsim.service.GroupService;
import cn.edu.ustc.wsim.service.MessageService;
import cn.edu.ustc.wsim.service.UserService;
import cn.edu.ustc.wsim.util.page.Page;
import cn.edu.ustc.wsim.websocket.room.RoomManager;

import com.opensymphony.xwork2.ActionSupport;

public class ManagerAction extends ActionSupport {
	
	private int currentPage;
	private Page page;
	
	private Integer userId;
	private Integer groupId;
	
	private User user;
	private Group group;
	
	private List<User> users;
	private List<Group> groups;
	
	private UserService userService;
	private GroupService groupService;
	
	private AdminService adminService;
	private MessageService messageService;
	private GroupMessageService groupMessageService;
	
	private long countUser;
	private long countGroup;
	private long countMessage;
	private long countGroupMessage;
	
	private long countAllMessage;
	
	private long countOnlineUser;
	private long countRooms;
	
	

	public String delUser() {
//		this.currentPage = currentPage;
		int uid = userId;
		if(userService.del(uid))
			return "delUser";
		else
			return "error";
	}
	
	public String delGroup() {
		int gid = groupId;
		if(groupService.del(gid))
			return "delGroup";
		else
			return "error";
	}
	
	
	public String searchUser() {
//		this.page = this.pageInfo();
//		Result result = this.userService.searchUserByPage(page);
//		page = result.getPage();
//		this.page = page;
//		this.users = result.getList();
		return "listUser";
	}
	
	
	public String searchGroup() {
//		this.page = this.pageInfo();
//		Result result = this.groupService.searchUserByPage(page);
//		page = result.getPage();
//		this.page = page;
//		this.groups = result.getList();
		return "listGroup";
	}
	
	
	public String count() {
		
		this.countUser = userService.count();
		this.countGroup = groupService.count();
		this.countMessage = messageService.count();
		this.countGroupMessage = groupMessageService.count();
		
		this.setCountAllMessage(countMessage + countGroupMessage);
		
		this.countOnlineUser = OnlineUsers.countOnlineUsers();
		this.countRooms = RoomManager.countRoom();
		
		return "count";
	}
	
	
	public Page pageInfo(){
		Page page = new Page();
		page.setEveryPage(10);
		page.setCurrentPage(this.getCurrentPage());
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public GroupMessageService getGroupMessageService() {
		return groupMessageService;
	}

	public void setGroupMessageService(GroupMessageService groupMessageService) {
		this.groupMessageService = groupMessageService;
	}

	public long getCountUser() {
		return countUser;
	}

	public void setCountUser(long countUser) {
		this.countUser = countUser;
	}

	public long getCountGroup() {
		return countGroup;
	}

	public void setCountGroup(long countGroup) {
		this.countGroup = countGroup;
	}

	public long getCountMessage() {
		return countMessage;
	}

	public void setCountMessage(long countMessage) {
		this.countMessage = countMessage;
	}

	public long getCountGroupMessage() {
		return countGroupMessage;
	}

	public void setCountGroupMessage(long countGroupMessage) {
		this.countGroupMessage = countGroupMessage;
	}

	public long getCountOnlineUser() {
		return countOnlineUser;
	}

	public void setCountOnlineUser(long countOnlineUser) {
		this.countOnlineUser = countOnlineUser;
	}

	public long getCountRooms() {
		return countRooms;
	}

	public void setCountRooms(long countRooms) {
		this.countRooms = countRooms;
	}

	public long getCountAllMessage() {
		return countAllMessage;
	}

	public void setCountAllMessage(long countAllMessage) {
		this.countAllMessage = countAllMessage;
	}

	

}
