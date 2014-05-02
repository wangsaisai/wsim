package cn.edu.ustc.wsim.action;

import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.datastructure.GlobalFinal;
import cn.edu.ustc.wsim.service.FriendGroupService;

import com.opensymphony.xwork2.ActionSupport;

public class FriendGroupAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6514900915029846668L;
	private Integer id;
	private Integer userId;
	private String name;
	
	private FriendGroupService friendGroupService;
	
	private String errorMsg;
	
	
	public String addFriendGroup() {
		if(name.equals(GlobalFinal.getDefaultFriendGroupName())) {
			errorMsg = "'My friends' 名字不能被使用";
			return "addError";
		}
		if(friendGroupService.isNameExist(friendGroupService.getLoginUser(), name))
		{
			errorMsg = "分组名已存在";
			return "addError";
		} else {
			FriendGroup friendGroup = new FriendGroup();
			friendGroup.setUser(friendGroupService.getLoginUser());
			friendGroup.setName(name);
			
			if(friendGroupService.add(friendGroup))
				return "addSuccess";
			else {
				errorMsg = "add FriendGroup error";
				return "addError";
			}
				
		}
	}
	
	public String delFriendGroup() {
//		int intId = id;
//		if(friendGroupService.del(intId))
//			return "delSuccess";
//		else {
//			errorMsg = "del error";
//			return "delError";
//		}
		return "";
	}
	
	public String changeName() {
		
		FriendGroup friendGroup = (FriendGroup) friendGroupService.get(id);
		
		if(friendGroupService.isDefaultFriendGroup(friendGroup)) {
			errorMsg = "'My Friends' 分组无法修改！";
			return "changeNameError";
		}
		
		if(name.equals(friendGroup.getName())) {
			errorMsg = "分组名与原来一致，请重新输入";
			return "changeNameError";
		}
		
		if(friendGroupService.isNameExist(friendGroupService.getLoginUser(), name)) {
			errorMsg = "分组名已存在，请重新输入";
			return "changeNameError";
		}
		
		friendGroup.setName(name);
		if(friendGroupService.update(friendGroup)) {
			return "changeNameSuccess";
		} else {
			errorMsg = "update Error";
			return "changeNameError";
		}
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FriendGroupService getFriendGroupService() {
		return friendGroupService;
	}

	public void setFriendGroupService(FriendGroupService friendGroupService) {
		this.friendGroupService = friendGroupService;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
