package cn.edu.ustc.wsim.websocket.group;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

import cn.edu.ustc.wsim.bean.User;


public class UserMessageInbound extends MessageInbound {

	private final User user;


	public UserMessageInbound(User user) {
		super();
		this.user = user;
	}


	//建立连接的触发的事件
	@Override
	protected void onOpen(WsOutbound outbound) {
		//向连接池添加当前的连接对象
		UserMessageInboundPool.addMessageInbound(this);
	}

	@Override
	protected void onClose(int status) {
		// 触发关闭事件，在连接池中移除连接
		UserMessageInboundPool.removeMessageInbound(this);
	}

	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		throw new UnsupportedOperationException("Binary message not supported.");
	}

	//客户端发送消息到服务器时触发事件
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
//		UserMessageInboundPool.sendMessage(sender, receiver, message.toString());
	}


	public User getUser() {
		return user;
	}
}
