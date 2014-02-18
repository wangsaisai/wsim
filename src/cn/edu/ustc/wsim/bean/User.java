package cn.edu.ustc.wsim.bean;

import java.util.HashSet;
import java.util.Set;

import cn.edu.ustc.wsim.enumerates.UserStatus;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

/*	//枚举，标识用户状态
	public enum UserStatus {
		ONLINE,			//在线
		INVISIBLE, 		//隐身	
		BUSY,			//忙碌
		DONOTDISTURB	//请勿打扰 do not disturb
	}

	//枚举，判断用户间关系
	public enum UserRelation {
		NOTFRIEND,	//非好友
		YN,			//YN单项好友，2是1的好友，1不是2的好友
		NY,			//单向好友，
		FRIEND		//互为好友
	}
*/
	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4796734099596619882L;
	private Integer id;
	private String email;
	private String password;
	private String name;
	private UserStatus status;
	
	private Set groupUsers = new HashSet(0);
	private Set groupmessages = new HashSet(0);
	private Set grouprequests = new HashSet(0);
	
	private Set messagesForSender = new HashSet(0);
	private Set messagesForReceiver = new HashSet(0);
	
	private Set reverseFriends = new HashSet(0);
	private Set friendgroups = new HashSet();
	
	private Set friendrequestsForResponder = new HashSet(0);
	private Set friendrequestsForRequester = new HashSet(0);
	

	// Constructors

	/** default constructor */
	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	/** minimal constructor */
	public User(Integer id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	/** full constructor */
	public User(Integer id, String email, String password, String name,
			UserStatus status, Set groupUsers, Set groupmessages,
			Set grouprequests, Set messagesForSender, Set messagesForReceiver,
			Set reverseFriends, Set friendgroups,
			Set friendrequestsForResponder, Set friendrequestsForRequester) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.status = status;
		this.groupUsers = groupUsers;
		this.groupmessages = groupmessages;
		this.grouprequests = grouprequests;
		this.messagesForSender = messagesForSender;
		this.messagesForReceiver = messagesForReceiver;
		this.reverseFriends = reverseFriends;
		this.friendgroups = friendgroups;
		this.friendrequestsForResponder = friendrequestsForResponder;
		this.friendrequestsForRequester = friendrequestsForRequester;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	public String getUserInfo(User user) {
		if(user.getName() == null || user.getName().equals(""))
			return user.getEmail();
		else
			return user.getName();
	}

	
	
	// Property accessors

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Set getGroupUsers() {
		return groupUsers;
	}

	public void setGroupUsers(Set groupUsers) {
		this.groupUsers = groupUsers;
	}

	public Set getGroupmessages() {
		return groupmessages;
	}

	public void setGroupmessages(Set groupmessages) {
		this.groupmessages = groupmessages;
	}

	public Set getGrouprequests() {
		return grouprequests;
	}

	public void setGrouprequests(Set grouprequests) {
		this.grouprequests = grouprequests;
	}

	public Set getMessagesForSender() {
		return messagesForSender;
	}

	public void setMessagesForSender(Set messagesForSender) {
		this.messagesForSender = messagesForSender;
	}

	public Set getMessagesForReceiver() {
		return messagesForReceiver;
	}

	public void setMessagesForReceiver(Set messagesForReceiver) {
		this.messagesForReceiver = messagesForReceiver;
	}

	public Set getReverseFriends() {
		return reverseFriends;
	}

	public void setReverseFriends(Set reverseFriends) {
		this.reverseFriends = reverseFriends;
	}

	public Set getFriendgroups() {
		return friendgroups;
	}

	public void setFriendgroups(Set friendgroups) {
		this.friendgroups = friendgroups;
	}

	public Set getFriendrequestsForResponder() {
		return friendrequestsForResponder;
	}

	public void setFriendrequestsForResponder(Set friendrequestsForResponder) {
		this.friendrequestsForResponder = friendrequestsForResponder;
	}

	public Set getFriendrequestsForRequester() {
		return friendrequestsForRequester;
	}

	public void setFriendrequestsForRequester(Set friendrequestsForRequester) {
		this.friendrequestsForRequester = friendrequestsForRequester;
	}

	
	

}