package cn.edu.ustc.wsim.bean;

import cn.edu.ustc.wsim.enumerates.GroupRole;

/**
 * GroupUser entity. @author MyEclipse Persistence Tools
 */

public class GroupUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 799255298063619785L;
	private Integer id;
	private Group group;
	private User user;
	private String remark;
	private GroupRole role;

	// Constructors

	/** default constructor */
	public GroupUser() {
	}

	/** minimal constructor */
	public GroupUser(Integer id, Group group, User user) {
		this.id = id;
		this.group = group;
		this.user = user;
	}
	
	public GroupUser(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public GroupUser(Integer id, Group group, User user, String remark, GroupRole role) {
		this.id = id;
		this.group = group;
		this.user = user;
		this.remark = remark;
		this.role = role;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public GroupRole getRole() {
		return role;
	}

	public void setRole(GroupRole role) {
		this.role = role;
	}

}