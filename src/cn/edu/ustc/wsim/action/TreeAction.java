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
	
	private List<FriendGroup> friendGroups;
	
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
				//显示好友备注名，若没有备注，则显示好友的网名
				String text = friend.getRemark();
				if(text == null || "".equals(text))
					text = friend.getUser().getName();
				friendJson.element("text", text);
				friendJson.element("leaf", true);
				friendArray.add(friendJson);
			}
			
			json.element("children", friendArray);
			
			array.add(json);
		}
		
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

	
}  