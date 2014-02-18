package cn.edu.ustc.wsim.websocket.room;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

public class RoomMessageInbound extends MessageInbound {

	private final String sender;
	private final Room room;


	public RoomMessageInbound(String sender, Room room) {
		super();
		this.sender = sender;
		this.room = room;
	}

	public String getSender() {
		return sender;
	}

	public Room getRoom() {
		return room;
	}

	//建立连接的触发的事件
	@Override
	protected void onOpen(WsOutbound outbound) {
		//向连接池添加当前的连接对象
		RoomMessageInboundPool.addMessageInbound(this);
	}

	@Override
	protected void onClose(int status) {
		// 触发关闭事件，在连接池中移除连接
		RoomMessageInboundPool.removeMessageInbound(this);
	}

	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		throw new UnsupportedOperationException("Binary message not supported.");
	}

	//客户端发送消息到服务器时触发事件
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		//向所有在线用户发送消息
		RoomMessageInboundPool.sendMessage(sender, room, message.toString());
	}
}
