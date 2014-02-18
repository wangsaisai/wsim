package cn.edu.ustc.wsim.bean;

import java.util.Date;

/**
 * Groupmessage entity. @author MyEclipse Persistence Tools
 */

public class GroupMessage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4628471359698412110L;
	private Integer id;
	private User user;
	private Group group;
	private String content;
	private Date time;

	// Constructors

	/** default constructor */
	public GroupMessage() {
	}
	
	public GroupMessage(Integer id) {
		this.id = id;
	}	

	/** full constructor */
	public GroupMessage(Integer id, User user, Group group, String content,
			Date time) {
		this.id = id;
		this.user = user;
		this.group = group;
		this.content = content;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}