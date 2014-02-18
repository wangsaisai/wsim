package cn.edu.ustc.wsim.websocket.friend;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

import cn.edu.ustc.wsim.bean.User;


public class FriendMessageInbound extends MessageInbound {

	private final User sender;
	private final User receiver;


	public FriendMessageInbound(User sender, User receiver) {
		super();
		this.sender = sender;
		this.receiver = receiver;
	}

	public User getSender() {
		return sender;
	}

	public User getReceiver() {
		return receiver;
	}

	//建立连接的触发的事件
	@Override
	protected void onOpen(WsOutbound outbound) {
		//向连接池添加当前的连接对象
		FriendMessageInboundPool.addMessageInbound(this);
//		System.out.println("sender: " + sender.getId() + " , receiver:" + receiver.getId() + ", 创建连接");
	}

	@Override
	protected void onClose(int status) {
		// 触发关闭事件，在连接池中移除连接
		FriendMessageInboundPool.removeMessageInbound(this);
//		System.out.println("sender: " + sender.getId() + " , receiver:" + receiver.getId() + ", 关闭连接");
	}

	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		throw new UnsupportedOperationException("Binary message not supported.");
	}

	//客户端发送消息到服务器时触发事件
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		//向所有在线用户发送消息
		FriendMessageInboundPool.sendMessage(sender, receiver, message.toString());
	}
}
