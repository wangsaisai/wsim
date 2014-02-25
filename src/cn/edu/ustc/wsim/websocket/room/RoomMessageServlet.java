package cn.edu.ustc.wsim.websocket.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;


@WebServlet(urlPatterns = { "/room.ws"})
//如果要接收浏览器的ws://协议的请求就必须实现WebSocketServlet这个类
public class RoomMessageServlet extends org.apache.catalina.websocket.WebSocketServlet {

	private static final long serialVersionUID = 1L;
	

	//跟平常Servlet不同的是，需要实现createWebSocketInbound，在这里初始化自定义的WebSocket连接对象
    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol,HttpServletRequest request) {
    	String user = request.getParameter("user");
    	String roomStr = request.getParameter("roomId");
    	Integer roomId;
		try {
			roomId = Integer.parseInt(roomStr);
//			Room room = RoomManager.getRoom(roomId);
	        return new RoomMessageInbound(user, roomId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
    }
}
