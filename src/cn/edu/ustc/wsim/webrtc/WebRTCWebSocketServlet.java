package cn.edu.ustc.wsim.webrtc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@WebServlet(urlPatterns = { "/websocket.ws"})
public class WebRTCWebSocketServlet extends WebSocketServlet {

	private static final long serialVersionUID = 1L;

	private String self;
	private String other;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.self = request.getParameter("self");
		this.other = request.getParameter("other");
		super.doGet(request, response);
	}

    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol,HttpServletRequest request) {
        return new WebRTCMessageInbound(self + "_" + other);
    }
}
