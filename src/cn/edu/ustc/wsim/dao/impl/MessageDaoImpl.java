package cn.edu.ustc.wsim.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import cn.edu.ustc.wsim.bean.Message;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.MessageDao;
import cn.edu.ustc.wsim.util.page.Page;

public class MessageDaoImpl extends BaseDaoImpl implements MessageDao {

	@Override
	public boolean del(int id) {
		return del(get(id));
	}
	
	@Override
	public Message get(int id) {
		return (Message) get("cn.edu.ustc.wsim.bean.Message", id);
	}

	@Override
	public long count() {
		String hsql = "select count(*) from Message";
		List count = super.getHibernateTemplate().find(hsql);
		return (Long) count.get(0);
	}

	@Override
	public List<Message> getMessages(User user1, User user2) {
//		String hsql = "from Message where (sender = )";
		Query query = super.getSession().createQuery("from Message where (sender = ? and receiver = ?) or (sender = ? and receiver = ?) order by time");
		query.setInteger(0, user1.getId());
		query.setInteger(1, user2.getId());
		query.setInteger(2, user2.getId());
		query.setInteger(3, user1.getId());
		return query.list();
	}
	
	
	public Long countMessagesByTime(User user1, User user2,
			Date beginTime, Date endTime) {
		Query query = super.getSession().createQuery("select count(*) from Message where ( (sender = ? and receiver = ?) or (sender = ? and receiver = ?) ) and (time > ? and time < ?) order by time");
		query.setInteger(0, user1.getId());
		query.setInteger(1, user2.getId());
		query.setInteger(2, user2.getId());
		query.setInteger(3, user1.getId());
		query.setDate(4, beginTime);
		query.setDate(5, endTime);
		List count = query.list();
		return (Long) count.get(0);
	}
	

	@Override
	public List<Message> getMessagesByTime(User user1, User user2,
			Date beginTime, Date endTime, Page page) {
		Query query = super.getSession().createQuery("from Message where ( (sender = ? and receiver = ?) or (sender = ? and receiver = ?) ) and (time > ? and time < ?) order by time");
		query.setInteger(0, user1.getId());
		query.setInteger(1, user2.getId());
		query.setInteger(2, user2.getId());
		query.setInteger(3, user1.getId());
		query.setDate(4, beginTime);
		query.setDate(5, endTime);
		
		// 设置每页显示多少个，设置多大结果。
		query.setMaxResults(page.getEveryPage());
		// 设置起点
		query.setFirstResult(page.getBeginIndex());
		return query.list();
	}

	@Override
	public List<Message> getUnreadMessagesOfUser(User user) {
		String hsql = "from Message where receiver = " + user.getId() + " and readed = false order by time";
		return super.getHibernateTemplate().find(hsql);
	}

}
