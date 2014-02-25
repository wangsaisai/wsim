package cn.edu.ustc.wsim.websocket.room;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import net.sf.json.JSONObject;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

public class RoomMessageInbound extends MessageInbound {

	private final String user;
	private final Integer roomId;


	public RoomMessageInbound(String user, Integer room) {
		super();
		this.user = user;
		this.roomId = room;
	}

	public String getUser() {
		return user;
	}

	public Integer getRoomId() {
		return roomId;
	}

	
	//建立连接的触发的事件
	@Override
	protected void onOpen(WsOutbound outbound) {
		/*
		 * step1 : 向聊天室所有用户(不包括该用户自己)发送当前用户上线信息。客户端用户栏回添加此用户
		 * step2 : 将该用户添加至连接池中
		 * step3 : 向该用户发送该聊天室所有用户列表
		 * 注：step1 和 step2 顺序颠倒时，会在客户端显示两个"自己"
		 */
		
		//向该聊天室中所有用户发送当前用户上线的消息
		JSONObject result = new JSONObject();
		result.element("type", "user_join");
		result.element("user", this.user);
		RoomMessageInboundPool.sendMessage(this.roomId, result.toString());
		
		//向连接池添加当前的连接对象
		RoomMessageInboundPool.addMessageInbound(this);
				
		//向当前连接发送当前在线用户的列表
		result = new JSONObject();
		result.element("type", "get_online_user");
		result.element("list", RoomMessageInboundPool.getRoomUsers(this.roomId));
		
		RoomMessageInboundPool.sendMessageToUser(this.roomId, this.user, result.toString());
	}
	

	@Override
	protected void onClose(int status) {
		// 触发关闭事件，在连接池中移除连接
		RoomMessageInboundPool.removeMessageInbound(this);
		
		//向该聊天室中所有用户发送当前用户退出的消息
		JSONObject result = new JSONObject();
		result.element("type", "user_leave");
		result.element("user", this.user);
		RoomMessageInboundPool.sendMessage(this.roomId, result.toString());
		
		//从room中清除该用户
		RoomManager.quitRoom(user, roomId);
	}
	

	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		throw new UnsupportedOperationException("Binary message not supported.");
	}

	//客户端发送消息到服务器时触发事件
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		//向所有在线用户发送消息
		RoomMessageInboundPool.sendMessage(roomId, message.toString());
	}
}
