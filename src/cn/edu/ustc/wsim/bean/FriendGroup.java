package cn.edu.ustc.wsim.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Friendgroup entity. @author MyEclipse Persistence Tools
 */

public class FriendGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1643273326264712689L;
	private Integer id;
	private User user;
	private String name;
	private Set friends = new HashSet(0);

	// Constructors

	/** default constructor */
	public FriendGroup() {
	}

	public FriendGroup(Integer id) {
		this.id = id;
	}
	
	/** minimal constructor */
	public FriendGroup(Integer id, User user, String name) {
		this.id = id;
		this.user = user;
		this.name = name;
	}

	/** full constructor */
	public FriendGroup(Integer id, User user, String name, Set friends) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.friends = friends;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getFriends() {
		return friends;
	}

	public void setFriends(Set friends) {
		this.friends = friends;
	}


}