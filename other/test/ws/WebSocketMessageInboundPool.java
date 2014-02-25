package cn.edu.ustc.wsim.websocket;

import java.util.HashMap;
import java.util.Map;

public class WebSocketMessageInboundPool {
	
	/*
	 * connections 存储所有的websocket链接，
	 * 其中包含了所有已登录用户的链接，
	 * 
	 */
	private static final Map<Integer, WebSocketMessageInbound> connections = new HashMap<Integer, WebSocketMessageInbound>();
	
	
	
	public Map getConnections() {
		return connections;
	}
	
	
	public WebSocketMessageInbound getConnection(Integer userId) {
		if(connections.containsKey(userId))
			return connections.get(userId);
		else
			return null;
	}
	
	
	//用户是否登录
	public boolean isLogin(Integer userId) {
		return connections.containsKey(userId);
	} 
	
	
	//统计所有在线用户数
	public Integer countOnlineUsers() {
		return connections.size();
	}
	
	
	
	
	
	/*
	 * 用户登录后就建立一条websocket链接
	 * 所有的通信都使用这条链接
	 * 断开链接，则表示用户已下线
	 */
	//向连接池中添加连接
	public static void addMessageInbound(WebSocketMessageInbound inbound){
		connections.put(inbound.getId(), inbound);
	}


	public static void removeMessageInbound(WebSocketMessageInbound inbound) {
		connections.remove(inbound.getId());
	}
	
	
	
	public static void sendMessageToUser(Integer receiverId, String message) {
		
	}
	
	
	public static void sendMessageToGroup(Integer groupId, String message) {
		
	}

	
	public static void sendMessageToRoom(Integer roomId, String message) {
		
	}
	
}
