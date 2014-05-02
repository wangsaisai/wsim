package cn.edu.ustc.wsim.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.edu.ustc.wsim.bean.Friend;
import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.service.FriendGroupService;
import cn.edu.ustc.wsim.service.FriendService;
import cn.edu.ustc.wsim.service.GroupUserService;

import com.opensymphony.xwork2.ActionSupport;

public class DisplayFriendsAndGroupsAction extends ActionSupport {

	private static final long serialVersionUID = -5995127811966376147L;

	private Integer userId;
	
	private FriendGroupService friendGroupService;
	private FriendService friendService;
	private GroupUserService groupUserService;
	
	private List<FriendGroup> friendGroups;
	private List groupUsers;
	
	@Override
	public String execute() {
		User loginUser = friendService.getLoginUser();
		userId = loginUser.getId();
		friendGroups = friendGroupService.getAllFriendGroupOfUser(loginUser);
		for (FriendGroup friendGroup : friendGroups) {
			Set friends = new HashSet<Friend>();
			friends.addAll(friendService.getFriendsOfFriendGroup(friendGroup));
			friendGroup.setFriends(friends);
		}
		
		this.setGroupUsers(groupUserService.getGroupUsersByUser(loginUser));
		return SUCCESS;
	}
	
	

	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public List<FriendGroup> getFriendGroups() {
		return friendGroups;
	}


	public void setFriendGroups(List<FriendGroup> friendGroups) {
		this.friendGroups = friendGroups;
	}


	public FriendGroupService getFriendGroupService() {
		return friendGroupService;
	}


	public void setFriendGroupService(FriendGroupService friendGroupService) {
		this.friendGroupService = friendGroupService;
	}


	public FriendService getFriendService() {
		return friendService;
	}


	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	
	public GroupUserService getGroupUserService() {
		return groupUserService;
	}


	public void setGroupUserService(GroupUserService groupUserService) {
		this.groupUserService = groupUserService;
	}


	public List getGroupUsers() {
		return groupUsers;
	}


	public void setGroupUsers(List groupUsers) {
		this.groupUsers = groupUsers;
	}


}
