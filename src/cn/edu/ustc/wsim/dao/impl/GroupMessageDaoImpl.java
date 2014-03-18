package cn.edu.ustc.wsim.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.GroupMessage;
import cn.edu.ustc.wsim.dao.GroupMessageDao;
import cn.edu.ustc.wsim.util.page.Page;

public class GroupMessageDaoImpl extends BaseDaoImpl implements GroupMessageDao {

	@Override
	public boolean del(int id) {
		return del(get(id));
	}
	
	@Override
	public GroupMessage get(int id) {
		return (GroupMessage) get("cn.edu.ustc.wsim.bean.GroupMessage", id);
	}

	@Override
	public long count() {
		String hsql = "select count(*) from GroupMessage";
		List count = super.getHibernateTemplate().find(hsql);
		return (Long) count.get(0);
	}

	@Override
	public List<GroupMessage> getGroupMessages(Group group) {
		String hsql = "from GroupMessage where groupId = " + group.getId() + " order by time desc";
		return super.getHibernateTemplate().find(hsql);
	}
	
	
	@Override
	public Long countGroupMessagesByTime(Group group,
			Date beginTime, Date endTime) {
		String hsql = "select count(*) from GroupMessage where groupId = ? and time >= ? and time <= ? order by time";
		Query query = super.getSession().createQuery(hsql);
		query.setInteger(0, group.getId());
		query.setDate(1, beginTime);
		query.setDate(2, endTime);
		List count = query.list();
		return (Long) count.get(0);
	}

	@Override
	public List<GroupMessage> getGroupMessagesByTime(final Group group,
			final Date beginTime, final Date endTime, final Page page) {
		//QQQQQQQQQQQQQQQQQ
//		String hsql = "from GroupMessage where groupId = " + group.getId() + " and time>=? and time<=? order by time desc";
		return super.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from GroupMessage where groupId = ? and time >= ? and time <= ? order by time");
				query.setInteger(0, group.getId());
				query.setDate(1, beginTime);
				query.setDate(2, endTime);
				
				// 设置每页显示多少个，设置多大结果。
				query.setMaxResults(page.getEveryPage());
				// 设置起点
				query.setFirstResult(page.getBeginIndex());
				return query.list();
			}
		});
	}

	
	
}
