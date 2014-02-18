package cn.edu.ustc.wsim.bean;

import cn.edu.ustc.wsim.enumerates.GroupRequestResult;

/**
 * Grouprequest entity. @author MyEclipse Persistence Tools
 */

public class GroupRequest implements java.io.Serializable {

	//枚举，标识加群请求结果
//	public enum GroupRequestResult {
//		UNDEAL,		//未处理
//		AGREE, 		//接受	
//		DENY		//拒绝
//	}
	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2412608197363860977L;
	private Integer id;
	private Group group;
	private User user;
	private GroupRequestResult result;
	private String remark;

	// Constructors

	/** default constructor */
	public GroupRequest() {
	}

	public GroupRequest(Integer id) {
		this.id = id;
	}
	
	/** full constructor */
	public GroupRequest(Integer id, Group group, User user, GroupRequestResult result, String remark) {
		this.id = id;
		this.group = group;
		this.user = user;
		this.result = result;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GroupRequestResult getResult() {
		return this.result;
	}

	public void setResult(GroupRequestResult result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}