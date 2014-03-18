package cn.edu.ustc.wsim.service.impl;

import java.util.Date;
import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupMessage;
import cn.edu.ustc.wsim.bean.Message;
import cn.edu.ustc.wsim.dao.GroupMessageDao;
import cn.edu.ustc.wsim.service.GroupMessageService;
import cn.edu.ustc.wsim.util.page.Page;
import cn.edu.ustc.wsim.util.page.PageUtil;
import cn.edu.ustc.wsim.util.page.Result;

public class GroupMessageServiceImpl extends BaseServiceImpl implements GroupMessageService {

	private GroupMessageDao groupMessageDao;
	
	public GroupMessageDao getGroupMessageDao() {
		return groupMessageDao;
	}

	public void setGroupMessageDao(GroupMessageDao groupMessageDao) {
		this.groupMessageDao = groupMessageDao;
	}

	@Override
	public long count() {
		return groupMessageDao.count();
	}

	@Override
	public GroupMessage get(int id) {
		return (GroupMessage) groupMessageDao.get(id);
	}
	
	@Override
	public boolean del(int id) {
		return groupMessageDao.del(id);
	}

	@Override
	public List<GroupMessage> getGroupMessages(Group group) {
		if(group != null && group.getId() != null)
			return groupMessageDao.getGroupMessages(group);
		else
			return null;
	}

	@Override
	public Result getGroupMessagesByTime(Group group,
			Date beginTime, Date endTime, Page page) {
		page = PageUtil.createPage(page, groupMessageDao.countGroupMessagesByTime(group, beginTime, endTime).intValue());
		List<GroupMessage> groupMessages = groupMessageDao.getGroupMessagesByTime(group, beginTime, endTime, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(groupMessages);
		return result;
	}

}
