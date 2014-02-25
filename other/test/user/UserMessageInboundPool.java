package cn.edu.ustc.wsim.websocket.group;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import cn.edu.ustc.wsim.bean.Group;
import cn.edu.ustc.wsim.bean.User;


public class UserMessageInboundPool {

	//保存连接的MAP容器
	private static final Map<Integer,UserMessageInbound > connections = new HashMap<Integer,UserMessageInbound>();
	
	
//	//生成key
//	private static Integer genKey(User user) {
//		return user.getId();
//	}
	
	
	//向连接池中添加连接
	public static void addMessageInbound(UserMessageInbound inbound){
		connections.put(inbound.getUser().getId(), inbound);
	}
	
	
	////从连接池中删除连接
	public static void removeMessageInbound(UserMessageInbound inbound){
		connections.remove(inbound.getUser().getId());
	}
	
	
	//向特定的用户发送数据
	public static void sendMessage(User user ,String message){
		try {
			UserMessageInbound inbound = connections.get(user.getId());
			if(inbound != null){
				inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//根据type和user生成相应json类型的数据
	private static String getMessage(Integer type, User user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("user", user.getUserInfo(user));
		JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
		return json.toString();
	}
	
	//根据type和group生成相应json类型的数据
	private static String getMessage(Integer type, User user, Group group) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("user", user.getUserInfo(user));
		map.put("group", group.getName());
		JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
		return json.toString();
	}
	
	
	//通知当前在线用户，有好友请求信息。  10
	public static void sendFriendRequestMessage(User user, User requester) {
		String message = getMessage(10, requester);
		sendMessage(user, message);
	}
	
	
	//11
	public static void sendGroupRequestMessage(User createrOrManager, User user, Group group) {
		String message = getMessage(11, user, group);
		sendMessage(createrOrManager, message);
	}
	
	//20
	public static void sendAcceptFriendRequestMessage( User user, User friend) {
		String message = getMessage(20, friend);
		sendMessage(user, message);
	}
	
	//21
	public static void sendAcceptGroupRequestMessage(User createrOrManager,User user, Group group) {
		String message = getMessage(21, user, group);
		sendMessage(createrOrManager, message);
	}
	
	//30
	public static void sendFriendMessageComingMessage(User user, User friend) {
		String message = getMessage(30, friend);
		sendMessage(user, message);
	}
	
	//31
	public static void sendGroupMessageComingMessage(User createrOrManager, User user, Group group) {
		String message = getMessage(31, user, group);
		sendMessage(createrOrManager, message);
	}
	
	public static void main(String[] args) {
		User user = new User(1, "1@1.com", "11");
		System.out.println(getMessage(3, user));
	}
	
}
