package cn.edu.ustc.wsim.action;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.edu.ustc.wsim.bean.Friend;
import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.service.FriendGroupService;
import cn.edu.ustc.wsim.service.FriendService;
import cn.edu.ustc.wsim.service.GroupUserService;

import com.opensymphony.xwork2.ActionSupport;
  
public class GroupTreeAction extends ActionSupport {  
 
	private String result;
	
	private Integer userId;
	
	private GroupUserService groupUserService;
	
	private List<Group> groups;
	
	

	@Override
	public String execute() {
		
		User loginUser = groupUserService.getLoginUser();
		userId = loginUser.getId();
		groups = groupUserService.getGroupsByUser(loginUser);
		
		JSONArray array = new JSONArray();
		
		for(Group group : groups) {
			JSONObject json = new JSONObject();
			json.element("id", "g_" + group.getId());
			json.element("text", group.getName());
			json.element("leaf", true);
			array.add(json);
		}
		
//		System.out.println("grouplist:  "+array.toString());
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

	public GroupUserService getGroupUserService() {
		return groupUserService;
	}

	public void setGroupUserService(GroupUserService groupUserService) {
		this.groupUserService = groupUserService;
	}
	
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}  