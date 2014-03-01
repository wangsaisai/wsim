package cn.edu.ustc.wsim.action;

import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupUser;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.enumerates.GroupRole;
import cn.edu.ustc.wsim.service.GroupUserService;

import com.opensymphony.xwork2.ActionSupport;

public class GroupUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8197144279816723436L;
	private Integer id;
	private Integer groupId;
	private Integer userId;
	private String remark;

	private List groupUsers;

	private GroupUserService groupUserService;

	private String errorMsg;

	public String addGroupUser() {
		// 设默认备注名为用户额name
		if (remark == null || "".equals(remark))
			remark = groupUserService.getLoginUser().getName();

		GroupUser groupUser = new GroupUser();
		groupUser.setGroup(new Group(groupId));
		groupUser.setRemark(remark);
		groupUser.setUser(new User(userId));
		groupUser.setRole(GroupRole.USER);

		if (groupUserService.add(groupUser))
			return "addSuccess";
		else {
			errorMsg = "add GroupUser error";
			return "addError";
		}

	}

	public String listGroupOfUser() {
		User user = groupUserService.getLoginUser();
		userId = user.getId();
		this.setGroupUsers(groupUserService.getGroupUsersByUser(user));
		return "listGroupOfUser";
	}

	public String delGroupUser() {
		int intId = id;
		if (groupUserService.del(intId))
			return "delSuccess";
		else {
			errorMsg = "del error";
			return "delError";
		}
	}

	public String quitGroup() {
//		User user = groupUserService.getLoginUser();
//		Group group = new Group(groupId);
		int groupUserId = id;
		if (groupUserService.del(groupUserId))
			return "quitGroupSuccess";
		else
			return "quitGroupError";
	}

	// 修改群名片
	public String changeRemark() {
		User user = groupUserService.getLoginUser();
		Group group = new Group(groupId);
		GroupUser groupUser = (GroupUser) groupUserService.get(user, group);

		groupUser.setRemark(remark);
		if (groupUserService.update(groupUser)) {
			return "changeNameSuccess";
		} else {
			errorMsg = "change name Error";
			return "changeNameError";
		}
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public List getGroupUsers() {
		return groupUsers;
	}

	public void setGroupUsers(List groupUsers) {
		this.groupUsers = groupUsers;
	}


}
