package cn.edu.ustc.wsim.websocket.user;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import cn.edu.ustc.wsim.bean.FriendRequest;
import cn.edu.ustc.wsim.bean.GroupRequest;
import cn.edu.ustc.wsim.bean.Message;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.datastructure.ChattingGroups;
import cn.edu.ustc.wsim.datastructure.OnlineUsers;


public class UserMessageInboundPool {

	//保存连接的MAP容器
	private static final Map<Integer, UserMessageInbound > connections = new HashMap<Integer, UserMessageInbound>();
	
	
	//向连接池中添加连接
	public static void addMessageInbound(UserMessageInbound inbound){
		connections.put(inbound.getUserId(), inbound);
	}
	
	
	////从连接池中删除连接
	public static void removeMessageInbound(UserMessageInbound inbound){
		connections.remove(inbound.getUserId());
		
		//用户下线，从OnlineUsers中删除该用户
		OnlineUsers.removeUser(inbound.getUserId());
	}
	
	
	//向特定的用户发送数据
	public static void sendMessage(Integer userId, String message){
		try {
			UserMessageInbound inbound = connections.get(userId);
			if(inbound != null){
				inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//发送群请求信息
	public static void sendFriendRequestMessage(FriendRequest friendRequest) {
		//封装信息
		Map<String, Object> map = new  HashMap<String,Object>();
		map.put("type", "friendRequest");
		User requester = friendRequest.getRequester();
		map.put("requester", requester.getName() + "<" + requester.getEmail() + ">");
		map.put("remark", friendRequest.getRemark());
		JSONObject json = JSONObject.fromObject(map);
		//发送信息
		sendMessage(friendRequest.getResponder().getId(), json.toString());
	}
	
	
	//发送好友请求消息
	public static void sendGroupRequestMessage(User createrOrManager, GroupRequest groupRequest) {
		//封装信息
		Map<String, Object> map = new  HashMap<String,Object>();
		map.put("type", "groupRequest");
		User requester = groupRequest.getUser();
		map.put("requester", requester.getName() + "<" + requester.getEmail() + ">");
		map.put("remark", groupRequest.getRemark());
		JSONObject json = JSONObject.fromObject(map);
		//发送信息
		sendMessage(createrOrManager.getId(), json.toString());
	}
	
	
	public static void sendUnreadMessages(User receiver, Map<User, List<Message>> messageMap) {
		
		for(Map.Entry mapEntry : messageMap.entrySet()) {
			User sender = (User) mapEntry.getKey();
			List<Message> messages = (List<Message>) mapEntry.getValue();
			
			Map<String, Object> map = new  HashMap<String,Object>();
			map.put("type", "OfflineMessages");
			map.put("messages", messages);		//concern QQQQQQQQQQQQQ
			JSONObject json = JSONObject.fromObject(map);
			
			sendMessage(receiver.getId(), json.toString());
		}
	}
	
	
	//两人通信
	public static void sendFriendMessage(Integer userId, String message) {
		sendMessage(userId, message);
	}
	
	
	//群通信
	public static void sendGroupMessage(Integer groupId, String message) {
		//获取当前在线群用户
		Set<User> users = ChattingGroups.getCGroup(groupId).getUsers();
		for (User user : users) {
			sendMessage(user.getId(), message);
		}
	}
	
	
	
	//just test
//	public static void sendNotify(Integer userId, String message) {
//		Map<String, Object> map = new  HashMap<String,Object>();
//		map.put("type", "notify");
//		map.put("message", "nnnnnotify");
//		JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
//		sendMessage(userId, json.toString());
//	}
	
	
	//根据type和user生成相应json类型的数据
//	private static String getMessage(String type, User user) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("type", type);
//		map.put("user", user.getUserInfo(user));
//		JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
//		return json.toString();
//	}
	
	//根据type和group生成相应json类型的数据
//	private static String getMessage(Integer type, User user, Group group) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("type", type);
//		map.put("user", user.getUserInfo(user));
//		map.put("group", group.getName());
//		JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
//		return json.toString();
//	}
//	
//	
//	//通知当前在线用户，有好友请求信息。  10
//	public static void sendFriendRequestMessage(User user, User requester) {
//		String message = getMessage(10, requester);
//		sendMessage(user, message);
//	}
//	
//	
//	//11
//	public static void sendGroupRequestMessage(User createrOrManager, User user, Group group) {
//		String message = getMessage(11, user, group);
//		sendMessage(createrOrManager, message);
//	}
//	
//	//20
//	public static void sendAcceptFriendRequestMessage( User user, User friend) {
//		String message = getMessage(20, friend);
//		sendMessage(user, message);
//	}
//	
//	//21
//	public static void sendAcceptGroupRequestMessage(User createrOrManager,User user, Group group) {
//		String message = getMessage(21, user, group);
//		sendMessage(createrOrManager, message);
//	}
//	
//	//30
//	public static void sendFriendMessageComingMessage(User user, User friend) {
//		String message = getMessage(30, friend);
//		sendMessage(user, message);
//	}
//	
//	//31
//	public static void sendGroupMessageComingMessage(User createrOrManager, User user, Group group) {
//		String message = getMessage(31, user, group);
//		sendMessage(createrOrManager, message);
//	}
//	
//	public static void main(String[] args) {
//		User user = new User(1, "1@1.com", "11");
//		System.out.println(getMessage(3, user));
//	}
	
}
