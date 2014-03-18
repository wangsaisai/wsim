package cn.edu.ustc.wsim.dao;

import java.util.Date;
import java.util.List;

import cn.edu.ustc.wsim.bean.Message;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.util.page.Page;

public interface MessageDao extends BaseDao {
	
	//查询两人聊天记录
	public List<Message> getMessages(User user1, User user2);
	
	public Long countMessagesByTime(User user1, User user2,
			Date beginTime, Date endTime);
	
	//根据时间查询聊天记录
	public List<Message> getMessagesByTime(User user1, User user2, Date beginTime, Date endTime, Page page);
	
	//获取离线消息
	public List<Message> getUnreadMessagesOfUser(User user);

}
