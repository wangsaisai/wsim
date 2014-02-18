package cn.edu.ustc.wsim.websocket.room;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class RoomMessageInboundPool {

	//保存连接的MAP容器
	private static final Map<String,RoomMessageInbound > connections = new HashMap<String,RoomMessageInbound>();
	
	
	private static String genKey(RoomMessageInbound inbound) {
		return inbound.getSender() + "_" + inbound.getRoom().getId();
	}
	
	//向连接池中添加连接
	public static void addMessageInbound(RoomMessageInbound inbound){
		connections.put(genKey(inbound), inbound);
	}
	
	
	//从连接池中删除连接
	public static void removeMessageInbound(RoomMessageInbound inbound){
		connections.remove(genKey(inbound));
		inbound.getRoom().rmUser(inbound.getSender());
	}
	
	
	//向所有的用户发送消息
	public static void sendMessage(String sender, Room room ,String message){
		try {
			Set<String> users = room.getUsers();
			message = sender + ": " + message;
			for (String key : users) {
				key = key + "_" + room.getId();
				RoomMessageInbound inbound = connections.get(key);
				if(inbound != null){
					inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
