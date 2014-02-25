package cn.edu.ustc.wsim.websocket.friend;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;

import cn.edu.ustc.wsim.bean.User;


@WebServlet(urlPatterns = { "/friend.ws"})
//如果要接收浏览器的ws://协议的请求就必须实现WebSocketServlet这个类
public class FriendMessageServlet extends org.apache.catalina.websocket.WebSocketServlet {

	private static final long serialVersionUID = 1L;
	

	//跟平常Servlet不同的是，需要实现createWebSocketInbound，在这里初始化自定义的WebSocket连接对象
    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol,HttpServletRequest request) {
    	String senderStr = request.getParameter("sender");
    	String receiverStr = request.getParameter("receiver");
    	User sender;
		User receiver;
		try {
			sender = new User(Integer.parseInt(senderStr));
			receiver = new User(Integer.parseInt(receiverStr));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		return new FriendMessageInbound(sender, receiver);
    }
}
