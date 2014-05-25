package cn.edu.ustc.wsim.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.edu.ustc.wsim.bean.Message;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.util.page.Page;
import cn.edu.ustc.wsim.util.page.Result;

public interface MessageService extends BaseService {
	
	//查询两人聊天记录
	public List<Message> getMessages(User user1, User user2);
	
	//根据时间查询聊天记录
	public Result getMessagesByTime(User user1, User user2, Date beginTime, Date endTime, Page page);
	
	//获取离线消息
	public Map<User, List<Message>> getUnreadMessagesOfUser(User user);
	
	public List<Message> getUnreadMessage(User user);

}
