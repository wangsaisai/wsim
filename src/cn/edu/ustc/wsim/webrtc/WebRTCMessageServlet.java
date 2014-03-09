package cn.edu.ustc.wsim.webrtc;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns = {"/message.servlet"})
public class WebRTCMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String self = request.getParameter("self");
		String other = request.getParameter("other");
		
	    BufferedReader br = request.getReader();
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line); //获取输入流，主要是视频定位的信息
        }
		
		String message = sb.toString();
		JSONObject json = JSONObject.fromObject(message);
		if (json != null) {
			String type = json.getString("type");
			if ("bye".equals(type)) {//客户端退出视频聊天
				System.out.println("user :" + self + " exit..");
			}
		}
		//向对方发送连接数据
		WebRTCMessageInboundPool.sendMessage(other + "_" + self, message);
	}
}
