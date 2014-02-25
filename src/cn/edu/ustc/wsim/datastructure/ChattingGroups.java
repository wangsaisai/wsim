package cn.edu.ustc.wsim.datastructure;

import java.util.HashMap;
import java.util.Map;

import cn.edu.ustc.wsim.bean.User;

public class ChattingGroups {
	
	
	private static final Map<Integer, CGroup> cgroups = new HashMap<Integer, CGroup>();
	

	public static CGroup addCGroup(Integer id) {
		if(cgroups.containsKey(id))
			return cgroups.get(id);
		else {
			CGroup cgroup = new CGroup(id);
			cgroups.put(id, cgroup);
			return cgroup;
		}
	}


	public static void rmCGroup(Integer id) {
		if(cgroups.containsKey(id))
			cgroups.remove(id);
	}
	
	
	public static void addUser(Integer id, User user) {
		CGroup cgroup = addCGroup(id);
		cgroup.getUsers().add(user);
	}
	
	
	public static void rmUser(Integer id, User user) {
		CGroup cgroup = cgroups.get(id);
		if(cgroup != null) {
			cgroup.rmUser(user);
			if(cgroup.getUsers().size() == 0)
				rmCGroup(id);
		}
	}
	
	
	public static CGroup getCGroup(Integer id) {
		if(cgroups.containsKey(id))
			return cgroups.get(id);
		else
			return null;
	}
	
	
	public static Integer countCGroup() {
		return cgroups.size();
	}
	
	
	
	//////////////////////////////
	public static void show() {
		System.out.println("在线群数： " + countCGroup());
	}
	

}
