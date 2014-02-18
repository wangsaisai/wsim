package cn.edu.ustc.wsim.service.impl;

import java.util.Date;
import java.util.List;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupMessage;
import cn.edu.ustc.wsim.dao.GroupMessageDao;
import cn.edu.ustc.wsim.service.GroupMessageService;

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
	public List<GroupMessage> getGroupMessagesByTime(Group group,
			Date beginTime, Date endTime) {
//		  sql=sql+"and time > ? and time < ?"; 
//		  return (List)getHibernateTemplate().find(sql,new Date[]{etime,ltime});
//		  注意，这find（）中必须是newDate[]{ time1,time2}。这是必须的！其中time1和time2 是Date 型的数据！
		return groupMessageDao.getGroupMessagesByTime(group, beginTime, endTime);
	}

}
