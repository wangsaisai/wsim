package cn.edu.ustc.wsim.websocket.friend;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;

import cn.edu.ustc.wsim.bean.User;


public class FriendMessageInboundPool {

	//保存连接的MAP容器
	private static final Map<String,FriendMessageInbound > connections = new HashMap<String,FriendMessageInbound>();
	
	
	//生成key，由发送者和接受者的id，在加上下划线，组成key
	private static String genKey(User sender, User receiver) {
		return sender.getId() + "_" + receiver.getId();
	}
	
	
	//向连接池中添加连接
	public static void addMessageInbound(FriendMessageInbound inbound){
		connections.put(genKey(inbound.getSender(), inbound.getReceiver()), inbound);
	}
	
	
	////从连接池中删除连接
	public static void removeMessageInbound(FriendMessageInbound inbound){
		connections.remove(genKey(inbound.getSender(), inbound.getReceiver()));
	}
	
	
	//向特定的用户发送数据
	public static void sendMessage(User sender, User receiver ,String message){
		try {
			FriendMessageInbound inbound = connections.get(genKey(receiver, sender));
			if(inbound != null){
				inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
