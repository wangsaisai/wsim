package cn.edu.ustc.wsim.websocket.room;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class RoomMessageInboundPool {
	
	//保存连接的MAP容器
	private static final Map<String,RoomMessageInbound > connections = new HashMap<String,RoomMessageInbound>();
	
	
	
	//生成Map对象的Key.		规则：user_roomId
	private static String genKey(RoomMessageInbound inbound) {
		return inbound.getUser() + "_" + inbound.getRoomId();
	}
	
	//生成Map对象的Key.		规则：user_roomId
	private static String genKey(String user, Integer roomId) {
		return user + "_" + roomId;
	}
	
	//获取room中所有用户
	public static Set<String> getRoomUsers(Integer roomId) {
		Room room = RoomManager.getRoom(roomId);
		if(room != null)
			return room.getUsers();
		else
			return null;
	}

	
	//向连接池中添加连接
	public static void addMessageInbound(RoomMessageInbound inbound){
		connections.put(genKey(inbound), inbound);
	}
	
	
	//从连接池中删除连接
	public static void removeMessageInbound(RoomMessageInbound inbound){
		connections.remove(genKey(inbound));
	}
	
	
	//向聊天室中所有的用户发送消息
	public static void sendMessage(Integer roomId ,String message){
		//当room被关闭，或者用户中没有users时，防止异常
		Set<String> users = getRoomUsers(roomId);
		if(users == null)
			return;
		for (String user : users) {
			String key = genKey(user, roomId);
			RoomMessageInbound inbound = connections.get(key);
			if(inbound != null){
				try {
					inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	//向特定用户发送消息
	public static void sendMessageToUser(Integer roomId, String user, String message) {
		String key = genKey(user, roomId);
		RoomMessageInbound inbound = connections.get(key);
		if(inbound != null) {
			try {
				inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
