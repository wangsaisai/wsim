package cn.edu.ustc.wsim.action;

import java.util.ArrayList;
import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupUser;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.enumerates.GroupRole;
import cn.edu.ustc.wsim.service.GroupService;
import cn.edu.ustc.wsim.service.GroupUserService;
import cn.edu.ustc.wsim.service.UserService;
import cn.edu.ustc.wsim.util.CheckSQLInject;

import com.opensymphony.xwork2.ActionSupport;

public class GroupAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -180414379954075082L;
	private Integer id;
	private Integer createrId;
	private String name;
	private Integer number;
	
	private List groups;
	
	private Group group;
	
	private GroupService groupService;
	private GroupUserService groupUserService;
	private UserService userService;
	
	private String errorMsg;
	
	private String searchinfo;
	private List<Boolean> relations;
	
	
	public String createGroup() {
		
		group = new Group();
		number = groupService.genGroupNumber();
		group.setNumber(number);
		group.setName(name);
		
		if(groupService.add(group)) {
			/*
			 * 将创建者加入群用户表中
			 */
			id = groupService.getGroupByNumber(number).getId();
			group.setId(id);
			GroupUser groupUser = new GroupUser();
			groupUser.setGroup(group);
			groupUser.setRemark(groupService.getLoginUser().getName());
			groupUser.setRole(GroupRole.CREATER);
			groupUser.setUser(groupService.getLoginUser());
			groupUserService.add(groupUser);
			
			return "createrSuccess";
		} else {
			errorMsg = "创建群失败，请重试";
			return "createError";
		}
	}
	
//	public String listGroupOfUser() {
//		User user = groupService.getLoginUser();
//		this.groups = groupUserService.getGroupsByUser(user);
//		return "listGroupOfUser";
//	}
	
	
	//该群相关的groupuser 和 grouprequest 都会被级联删除
	public String delGroup() {
		Group group = (Group) groupService.get(id);
		if(!groupUserService.isCreater(groupService.getLoginUser(), group) )
		{
			errorMsg = "只有群主才能删除群";
			return "delError";
		}
		int intId = id;
		if(groupService.del(intId))
			return "delSuccess";
		else {
			errorMsg = "del error";
			return "delError";
		}
	}
	
	public String updateGroup() {
		Group group = (Group) groupService.get(id);
		if(!groupUserService.isCreater(groupService.getLoginUser(), group) )
		{
			errorMsg = "只有群主才能修改群信息";
			return "updateError";
		}
		
		group.setName(name);
		if(groupService.update(group))
			return "updateSuccess";
		else {
			errorMsg = "update error";
			return "updateError";
		}
	}
	
	
	public String search() {
		if(CheckSQLInject.isSQLInject(searchinfo) || groupService.searchGroup(searchinfo) == null || groupService.searchGroup(searchinfo).size() == 0)
			return "cannotSearch";
		else {
			this.groups = groupService.searchGroup(searchinfo);
			
			User loginUser = groupService.getLoginUser();
			relations = new ArrayList<Boolean>();
			for (Group group : (List<Group>)groups) {
				if(groupUserService.isBelong(loginUser, group))
					relations.add(true);
				else
					relations.add(false);
			}
			return "searchSuccess";
		}
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List getGroups() {
		return groups;
	}

	public void setGroups(List groups) {
		this.groups = groups;
	}

	public String getSearchinfo() {
		return searchinfo;
	}

	public void setSearchinfo(String searchinfo) {
		this.searchinfo = searchinfo;
	}

	public List<Boolean> getRelations() {
		return relations;
	}

	public void setRelations(List<Boolean> relations) {
		this.relations = relations;
	}

	public GroupUserService getGroupUserService() {
		return groupUserService;
	}

	public void setGroupUserService(GroupUserService groupUserService) {
		this.groupUserService = groupUserService;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}


}
