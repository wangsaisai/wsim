package cn.edu.ustc.wsim.websocket.room;

import java.util.HashSet;
import java.util.Set;


public class Room {
	
	private Integer id;
	private Set<String> users = new HashSet<>();
	
	
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(Integer id) {
		super();
		this.id = id;
	}
	

	public void addUser(String user) {
		if(!users.contains(user))
			this.users.add(user);
	}
	
	public void rmUser(String user) {
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
		Room other = (Room) obj;
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

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}
	

}
