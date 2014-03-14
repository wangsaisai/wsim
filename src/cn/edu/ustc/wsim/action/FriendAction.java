package cn.edu.ustc.wsim.action;

import java.util.List;

import cn.edu.ustc.wsim.bean.Friend;
import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.service.FriendGroupService;
import cn.edu.ustc.wsim.service.FriendService;
import cn.edu.ustc.wsim.service.UserService;
import cn.edu.ustc.wsim.util.CheckSQLInject;

import com.opensymphony.xwork2.ActionSupport;

public class FriendAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2242880568587925448L;
	private Integer id;
	private Integer friendgroupId;
	private Integer userId;
	private String remark;
	
	private String searchinfo;
	
	private List<Friend> friends;
	
	private FriendService friendService;
	private FriendGroupService friendGroupService;
	private UserService userService;
	
	private String errorMsg;
	
	



	//not used
	public String addFriend() {
		
		Friend friend = new Friend();
		friend.setFriendgroup(new FriendGroup(friendgroupId));
		friend.setUser(new User(userId));
		friend.setRemark(remark);
		
		if(friendService.add(friend))
			return "addSuccess";
		else {
			errorMsg = "add Friend error";
			return "addError";
		}
				
	}
	
	//根据id删好友
	public String delFriend() {
		int intId = id;
		if(friendService.del(intId))
			return "delSuccess";
		else {
			errorMsg = "del error";
			return "delError";
		}
	}
	
	//根据好友id 删好友
	public String delFriendByUserId() {
		User loginUser = friendService.getLoginUser();
		User friendUser = new User(userId);
		if(!friendGroupService.isFriend(loginUser, friendUser)) {
			errorMsg = "对方不是您好友";
			return "delFriendError";
		}
		
		Friend friend = friendService.getFriend(loginUser, friendUser);
		if(friendService.del(friend))
			return "delFriendSuccess";
		else {
			errorMsg = "del friend error";
			return "delFriendError";
		}
			
			
	}
	
	public String changeRemark() {
		Friend friend = (Friend) friendService.get(id);
		friend.setRemark(remark);
		if(friendService.update(friend)) {
			return "changeRemarkSuccess";
		} else {
			errorMsg = "change remark error";
			return "changeRemarkError";
		}
	}
	
	//移动分组
	public String moveFriendGroup() {
		Friend friend = (Friend) friendService.get(id);
		friend.setFriendgroup(new FriendGroup(friendgroupId));
		if(friendService.update(friend)) {
			return "moveFriendGroupSuccess";
		} else {
			errorMsg = "move Friend Group error";
			return "moveFriendGroupError";
		}
	}
	
	
	public String search() {
		if(CheckSQLInject.isSQLInject(searchinfo) || friendService.searchFriend(searchinfo) == null || friendService.searchFriend(searchinfo).size() == 0) {
			return "cannotSearch";
		} else {
			this.friends = friendService.searchFriend(searchinfo);
			return "searchSuccess";
		}
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFriendgroupId() {
		return friendgroupId;
	}

	public void setFriendgroupId(Integer friendgroupId) {
		this.friendgroupId = friendgroupId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public FriendGroupService getFriendGroupService() {
		return friendGroupService;
	}

	public void setFriendGroupService(FriendGroupService friendGroupService) {
		this.friendGroupService = friendGroupService;
	}
	
	public String getSearchinfo() {
		return searchinfo;
	}

	public void setSearchinfo(String searchinfo) {
		this.searchinfo = searchinfo;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}


}
