package cn.edu.ustc.wsim.datastructure;

import java.util.HashSet;
import java.util.Set;

import cn.edu.ustc.wsim.bean.User;

public class CGroup {
	
	private Integer id;
	private Set<User> users = new HashSet<>();
	
	public CGroup() {}
	
	public CGroup(Integer id) {
		this.id = id;
	}
	
	public void addUser(User user) {
		if(!users.contains(user))
			this.users.add(user);
	}
	
	public void rmUser(User user) {
		if(users.contains(user))
			users.remove(user);
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
		CGroup other = (CGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	

}
