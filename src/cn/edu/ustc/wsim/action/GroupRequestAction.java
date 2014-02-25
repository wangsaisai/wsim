package cn.edu.ustc.wsim.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupRequest;
import cn.edu.ustc.wsim.bean.GroupUser;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.enumerates.GroupRequestResult;
import cn.edu.ustc.wsim.enumerates.GroupRole;
import cn.edu.ustc.wsim.service.GroupRequestService;
import cn.edu.ustc.wsim.service.GroupService;
import cn.edu.ustc.wsim.service.GroupUserService;
import cn.edu.ustc.wsim.websocket.user.UserMessageInboundPool;

import com.opensymphony.xwork2.ActionSupport;

public class GroupRequestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4557668899940742813L;
	private Integer id;
	private Integer groupId;
	private Integer userId;
	private GroupRequestResult result;
	private String remark;
	
	private String resultStr;
	
	private Integer groupNumber;
	
	private List<GroupRequest> groupRequests;
	private Map<Group, List<GroupRequest>> allGroupRequests;

	
	private GroupRequestService groupRequestService;
	private GroupService groupService;
	private GroupUserService groupUserService;
	
	private String errorMsg;
	
	
	public String addGroupRequest() {
		Group group = (Group) groupService.get(groupId);
		if(group == null) {
			errorMsg = "该群不存在";
			return "addError";
		}
		
		User loginUser = groupRequestService.getLoginUser();
		if(groupUserService.isBelong(loginUser, group)) {
			errorMsg = "您已经是该群成员";
			return "addError";
		}
		
		GroupRequest groupRequest = new GroupRequest();
		groupRequest.setGroup(group);
		groupRequest.setUser(loginUser);
		groupRequest.setResult(GroupRequestResult.UNDEAL);
		groupRequest.setRemark(remark);
		
		if(groupRequestService.add(groupRequest)) {
			//查找该群中在线的管理员或者群主，并向他发送通知
			//将id信息封装到newGroupRequest中
			GroupRequest newGroupRequest = groupRequestService.get(loginUser, group);
			User user = groupRequestService.getOnlineCreaterOrManager(group);
			if(user != null)
				UserMessageInboundPool.sendGroupRequestMessage(user, newGroupRequest);
			return "addSuccess";
		}
		else {
			errorMsg = "add GroupRequest error";
			return "addError";
		}
				
	}
	
	
	//not used
	public String delGroupRequest() {
		int intId = id;
		if(groupRequestService.del(intId))
			return "delSuccess";
		else {
			errorMsg = "del error";
			return "delError";
		}
	}
	
	public String dealGroupRequest() {
		
		switch (resultStr) {
		case "agree":
			result = GroupRequestResult.AGREE;
			break;
		case "deny":
			result = GroupRequestResult.DENY;
			break;
		default:
			break;
		}
		
		GroupRequest groupRequest = (GroupRequest) groupRequestService.get(id);
		groupRequest.setResult(result);
		
		if(groupRequestService.update(groupRequest)) {
			switch (result) {
			case AGREE:
				GroupUser gu = new GroupUser();
				gu.setGroup(groupRequest.getGroup());
				gu.setUser(groupRequest.getUser());
				gu.setRole(GroupRole.USER);
				groupUserService.add(gu);
				//给用户发送消息
				break;
			case DENY:
				//给用户发送消息
				break;
			default:
				errorMsg = "赞无法处理";
				return "dealGroupRequestError";
//				break;
			}
			return "dealGroupRequestSuccess";
		} else {
			errorMsg = "update Error";
			return "dealGroupRequestError";
		}
	}
	
	
	public String displayUndealGroupRequest() {
		this.allGroupRequests = groupRequestService.getUndealGroupRequests(groupRequestService.getLoginUser());
		return "listAllUndealGroupRequest";
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public GroupRequestResult getResult() {
		return result;
	}

	public void setResult(GroupRequestResult result) {
		this.result = result;
	}

	public Integer getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(Integer groupNumber) {
		this.groupNumber = groupNumber;
	}

	public GroupRequestService getGroupRequestService() {
		return groupRequestService;
	}

	public void setGroupRequestService(GroupRequestService groupRequestService) {
		this.groupRequestService = groupRequestService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public GroupUserService getGroupUserService() {
		return groupUserService;
	}

	public void setGroupUserService(GroupUserService groupUserService) {
		this.groupUserService = groupUserService;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public List<GroupRequest> getGroupRequests() {
		return groupRequests;
	}


	public void setGroupRequests(List<GroupRequest> groupRequests) {
		this.groupRequests = groupRequests;
	}


	public Map<Group, List<GroupRequest>> getAllGroupRequests() {
		return allGroupRequests;
	}


	public void setAllGroupRequests(Map<Group, List<GroupRequest>> allGroupRequests) {
		this.allGroupRequests = allGroupRequests;
	}


	public String getResultStr() {
		return resultStr;
	}


	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

}
