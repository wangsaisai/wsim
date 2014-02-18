package cn.edu.ustc.wsim.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Group entity. @author MyEclipse Persistence Tools
 */

public class Group implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -815710936262165531L;
	private Integer id;
	private String name;
	private Integer number;
	private Set grouprequests = new HashSet(0);
	private Set groupmessages = new HashSet(0);
	private Set groupUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Group() {
	}
	
	public Group(Integer id) {
		this.id = id;
	}
		
	/** minimal constructor */
	public Group(Integer id, String name, Integer number) {
		this.id = id;
		this.name = name;
		this.number = number;
	}

	/** full constructor */
	public Group(Integer id, String name, Integer number,
			Set grouprequests, Set groupmessages, Set groupUsers) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.grouprequests = grouprequests;
		this.groupmessages = groupmessages;
		this.groupUsers = groupUsers;
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
		Group other = (Group) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Set getGrouprequests() {
		return this.grouprequests;
	}

	public void setGrouprequests(Set grouprequests) {
		this.grouprequests = grouprequests;
	}

	public Set getGroupmessages() {
		return groupmessages;
	}

	public void setGroupmessages(Set groupmessages) {
		this.groupmessages = groupmessages;
	}

	public Set getGroupUsers() {
		return this.groupUsers;
	}

	public void setGroupUsers(Set groupUsers) {
		this.groupUsers = groupUsers;
	}

}