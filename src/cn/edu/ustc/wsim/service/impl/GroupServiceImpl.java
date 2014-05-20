package cn.edu.ustc.wsim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupUser;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.GroupDao;
import cn.edu.ustc.wsim.service.GroupService;
import cn.edu.ustc.wsim.service.GroupUserService;
import cn.edu.ustc.wsim.util.page.Page;
import cn.edu.ustc.wsim.util.page.PageUtil;
import cn.edu.ustc.wsim.util.page.Result;

public class GroupServiceImpl extends BaseServiceImpl implements GroupService {

	private GroupDao groupDao;
	
//	GroupUserService groupUserService;
//	
//	public GroupUserService getGroupUserService() {
//		return groupUserService;
//	}
//
//	public void setGroupUserService(GroupUserService groupUserService) {
//		this.groupUserService = groupUserService;
//	}

	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public long count() {
		return groupDao.count();
	}

	@Override
	public Group get(int id) {
		return (Group) groupDao.get(id);
	}
	
	@Override
	public boolean del(int id) {
		return groupDao.del(id);
	}

	@Override
	public Group getGroupByNumber(Integer number) {
		return groupDao.getGroupByNumber(number);
	}

	@Override
	public Integer genGroupNumber() {
		//生成8位以内的随机数
		int random = (int) (Math.random()*100000000);
		while(this.isGroupNumberExist(random))
			random = (int) (Math.random()*100000000);
		return random;
	}

	@Override
	public boolean isGroupNumberExist(Integer number) {
		return groupDao.isGroupNumberExist(number);
	}

	@Override
	public boolean createrGroup(Group group) {
		if(groupDao.add(group)) {
			return true;
		}
		else
			return false;
	}

//	@Override
//	public boolean isCreater(User user, Group group) {
//			return false;
//	}

	private boolean isGroupNumberType(String str) {
		String regGroupNumber ="[0-9]{1,8}";
		if(Pattern.compile(regGroupNumber).matcher(str).matches())
			return true;
		else
			return false;
	}

	@Override
	public List<Group> searchGroup(String searchinfo) {
		List<Group> groups = new ArrayList<>();
		
		if(this.isGroupNumberType(searchinfo)) {
			Integer groupNumber = Integer.parseInt(searchinfo);
			if(this.getGroupByNumber(groupNumber) != null)
				groups.add(this.getGroupByNumber(groupNumber));
		}
		
		groups.addAll(groupDao.searchGroupByName(searchinfo));
		
		return groups;
	}

	@Override
	public Result listGroup(Page page) {
		page = PageUtil.createPage(page, (int)groupDao.count());
		List<Group> groups = groupDao.listGroup(page);
		Result result = new Result();
		result.setPage(page);
		result.setList(groups);
		return result;
	}


}
