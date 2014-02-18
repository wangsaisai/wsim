package cn.edu.ustc.wsim.bean;

/**
 * Friend entity. @author MyEclipse Persistence Tools
 */

public class Friend implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6273352403738160676L;
	private Integer id;
	private FriendGroup friendgroup;
	private User user;
	private String remark;

	// Constructors

	/** default constructor */
	public Friend() {
	}
	
	public Friend(Integer id) {
		this.id = id;
	}
	
	/** minimal constructor */
	public Friend(Integer id, FriendGroup friendgroup, User user) {
		this.id = id;
		this.friendgroup = friendgroup;
		this.user = user;
	}

	/** full constructor */
	public Friend(Integer id, FriendGroup friendgroup, User user, String remark) {
		this.id = id;
		this.friendgroup = friendgroup;
		this.user = user;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FriendGroup getFriendgroup() {
		return this.friendgroup;
	}

	public void setFriendgroup(FriendGroup friendgroup) {
		this.friendgroup = friendgroup;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}