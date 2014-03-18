package cn.edu.ustc.wsim.action;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.edu.ustc.wsim.bean.Friend;
import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.service.FriendGroupService;
import cn.edu.ustc.wsim.service.FriendService;
import cn.edu.ustc.wsim.service.GroupUserService;

import com.opensymphony.xwork2.ActionSupport;
  
public class TreeAction extends ActionSupport {  
 
	private String result;
	
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
		
		
		
		JSONArray array = new JSONArray();
		for (FriendGroup friendGroup : friendGroups) {
			JSONObject json = new JSONObject();
			json.element("id", "fg_" + friendGroup.getId());
			json.element("text", friendGroup.getName());
			json.element("leaf", false);
			
			JSONArray friendArray = new JSONArray();
			for (Friend friend : (Set<Friend>)friendGroup.getFriends()) {
				JSONObject friendJson = new JSONObject();
				friendJson.element("id", "f_" + friend.getUser().getId());
				friendJson.element("text", friend.getUser().getName());
				friendJson.element("leaf", true);
				friendArray.add(friendJson);
			}
			
			json.element("children", friendArray);
			
			array.add(json);
		}
		
		
		
		
//		JSONArray array = new JSONArray();
//		for (int j = 0; j < 5; j++) {
//			JSONObject jsonc = new JSONObject();
//			jsonc.element("id", "aaaa" + j);
//			jsonc.element("text", "fenzu:11");
//			jsonc.element("leaf", true);
//			
//			
//			JSONObject json = new JSONObject();
//			json.element("id", "" + j);
//			json.element("text", "fenzu:"+ j);
//			json.element("leaf", false);
//			json.element("children", jsonc);
//			array.add(json);
//		}
		
		
		
		
		
		
		
		System.out.println("nodelist:  "+array.toString());
		setResult(array.toString());
		
		
		return SUCCESS;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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