package cn.edu.ustc.wsim.service.impl;

import java.util.List;

import cn.edu.ustc.wsim.bean.Friend;
import cn.edu.ustc.wsim.bean.FriendGroup;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.FriendGroupDao;
import cn.edu.ustc.wsim.dao.impl.FriendGroupDaoImpl;
import cn.edu.ustc.wsim.datastructure.GlobalFinal;
import cn.edu.ustc.wsim.service.FriendGroupService;
import cn.edu.ustc.wsim.service.FriendService;

public class FriendGroupServiceImpl extends BaseServiceImpl implements FriendGroupService {

	private FriendGroupDao friendGroupDao;
	private FriendService friendService;
	
	public FriendGroupDao getFriendGroupDao() {
		return friendGroupDao;
	}

	public void setFriendGroupDao(FriendGroupDao friendGroupDao) {
		this.friendGroupDao = friendGroupDao;
	}

	@Override
	public long count() {
		return friendGroupDao.count();
	}

	@Override
	public FriendGroup get(int id) {
		return (FriendGroup) friendGroupDao.get(id);
	}
	
	@Override
	public boolean del(int id) {
		return friendGroupDao.del(id);
	}

	@Override
	public List getAllFriendGroupOfUser(User user) {
//		if(friendGroupDao == null)
//			System.out.println("   f g null   ");
//		if(user == null)
//			System.out.println("    user null ");
//		friendGroupDao = new FriendGroupDaoImpl();
		return friendGroupDao.getAllFriendGroupOfUser(user);
	}

	@Override
	public FriendGroup getFriendGroupByName(User user, String name) {
		return friendGroupDao.getFriendGroupByName(user, name);
	}

	@Override
	public boolean isNameExist(User user, String name) {
		if(this.getFriendGroupByName(user, name) != null)
			return true;
		else
			return false;
	}

	@Override
	public void addDefaultFriendGroup(User user) {
		FriendGroup fg = new FriendGroup();
		fg.setUser(user);
		fg.setName(GlobalFinal.getDefaultFriendGroupName());
		friendGroupDao.add(fg);
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	@Override
	public boolean isFriend(User self, User other) {
//		FriendGroupService fgs = new FriendGroupServiceImpl();
//		FriendService fs = new FriendServiceImpl();
		for (FriendGroup fg : (List<FriendGroup>)this.getAllFriendGroupOfUser(self)) {
			for (Friend f : friendService.getFriendsOfFriendGroup(fg)) {
				if(f.getUser().equals(other))
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean isDefaultFriendGroup(FriendGroup friendGroup) {
		if(friendGroup.getName().equals(GlobalFinal.getDefaultFriendGroupName()))
			return true;
		else
			return false;
	}

}
