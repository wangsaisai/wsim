package cn.edu.ustc.wsim.action;

import java.util.Date;
import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupMessage;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.service.GroupMessageService;

import com.opensymphony.xwork2.ActionSupport;

public class GroupMessageAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 356204469919654786L;
	private Integer id;
	private Integer userId;
	private Integer groupId;
	private String content;
	private Date time;
	
	private GroupMessageService groupMessageService;
	
	private String errorMsg;
	
	private Date beginTime;
	private Date endTime;
	private List<GroupMessage> groupMessages;
	
	


	public String addGroupMessage() {
		
		GroupMessage groupMessage = new GroupMessage();
		groupMessage.setUser(new User(userId));
//		groupMessage
		
		if(groupMessageService.add(groupMessage))
			return "addSuccess";
		else {
			errorMsg = "add GroupMessage error";
			return "addError";
		}
				
	}
	
	public String delGroupMessage() {
		int intId = id;
		if(groupMessageService.del(intId))
			return "delSuccess";
		else {
			errorMsg = "del error";
			return "delError";
		}
	}
	
//	public String updateGroupMessage() {
//		
//		GroupMessage groupMessage = (GroupMessage) groupMessageService.get(id);
//
//		if(groupMessageService.update(groupMessage)) {
//			return "updateSuccess";
//		} else {
//			errorMsg = "update Error";
//			return "updateError";
//		}
//	}
	
	
	public String searchGroupMessageByTime() {
		Group group = new Group(groupId);
		this.groupMessages = groupMessageService.getGroupMessagesByTime(group, beginTime, endTime);
		if(groupMessages == null || groupMessages.size() == 0)
			return "noResult";
		else
			return "showResult";
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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public GroupMessageService getGroupMessageService() {
		return groupMessageService;
	}

	public void setGroupMessageService(GroupMessageService groupMessageService) {
		this.groupMessageService = groupMessageService;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<GroupMessage> getGroupMessages() {
		return groupMessages;
	}

	public void setGroupMessages(List<GroupMessage> groupMessages) {
		this.groupMessages = groupMessages;
	}

}
