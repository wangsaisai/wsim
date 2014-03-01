package cn.edu.ustc.wsim.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.edu.ustc.wsim.bean.Friend;
import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.service.FriendGroupService;
import cn.edu.ustc.wsim.service.FriendService;

import com.opensymphony.xwork2.ActionSupport;

public class DisplayFriends extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6930597197749051946L;
	private Integer userId;
	private List<FriendGroup> friendGroups;
	private FriendGroupService friendGroupService;
	private FriendService friendService;
	
	
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

}
