package cn.edu.ustc.wsim.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.ustc.wsim.bean.Message;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.dao.MessageDao;
import cn.edu.ustc.wsim.service.MessageService;

public class MessageServiceImpl extends BaseServiceImpl implements MessageService {

	private MessageDao messageDao;
	
	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public long count() {
		return messageDao.count();
	}

	@Override
	public Message get(int id) {
		return (Message) messageDao.get(id);
	}
	
	@Override
	public boolean del(int id) {
		return messageDao.del(id);
	}

	@Override
	public List<Message> getMessages(User user1, User user2) {
		return messageDao.getMessages(user1, user2);
	}

	@Override
	public List<Message> getMessagesByTime(User user1, User user2,
			Date beginTime, Date endTime) {
		return messageDao.getMessagesByTime(user1, user2, beginTime, endTime);
	}

	@Override
	public Map<User, List<Message>> getUnreadMessagesOfUser(User user) {
		List<Message> messages = messageDao.getUnreadMessagesOfUser(user);
		Map<User, List<Message>> map = new HashMap<User, List<Message>>();
		
		//将List对象messages， 根据message中的sender进行分组，
		for (Message message : messages) {
			if(map.containsKey(message)) {
				map.get(message).add(message);
			} else {
				List list = new ArrayList<>();
				list.add(message);
				map.put(message.getSender(), list);
			}
		}
		
		return map;
	}

}
