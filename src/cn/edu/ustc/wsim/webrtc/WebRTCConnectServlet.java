package cn.edu.ustc.wsim.webrtc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.ustc.wsim.websocket.user.UserMessageInboundPool;

@WebServlet(urlPatterns = {"/conn.servlet"})
public class WebRTCConnectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String type = request.getParameter("type");
		String self = request.getParameter("self");
		String other = request.getParameter("other");
		
		int initiator = 1;
		if("req".equals(type)) {
			initiator = 0;
			//通知用户other，有视频请求信息
			Integer selff = Integer.parseInt(self);
			Integer otherr = Integer.parseInt(other);
			UserMessageInboundPool.sendVideoRequestMessage(selff, otherr);
		}
			
		request.setAttribute("self", self);
		request.setAttribute("other", other);
		request.setAttribute("initiator", initiator);
		request.getRequestDispatcher("video.jsp").forward(request, response);
		
	}
}
