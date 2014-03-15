package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/acc.servlet"})
public class AccordionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		initHeader(response);
		if(action.equals("list")){//获取属面板列表
			renderText(this.getTreePanelList(), response);
		}else if(action.equals("node")){
			renderText(this.getTreeNodeList(request.getParameter("id")), response);
		}
	}
	
	public String getTreeNodeList(String id){
		
		JSONArray array = new JSONArray();
		for (int j = 0; j < 5; j++) {
			JSONObject jsonc = new JSONObject();
			jsonc.element("id", "aaaa" + j);
			jsonc.element("text", "fenzu:11");
			jsonc.element("leaf", true);
			
			
			JSONObject json = new JSONObject();
			json.element("id", "" + j);
			json.element("text", "fenzu:"+ j);
			json.element("leaf", false);
			json.element("children", jsonc);
			array.add(json);
		}
		
		System.out.println("nodelist:  "+array.toString());
		return array.toString();
	}
	
	public String getTreePanelList(){
		JSONArray array = new JSONArray();
		for (int i = 0; i < 5; i++) { //生成5个属面板
			JSONObject json = new JSONObject();
			json.element("id", i+1);
			json.element("iconCls", "icon-panel");
			json.element("title", "Accordion菜单"+(i+1));
			array.add(json);
		}
		
		System.out.println("panel list:  "+array.toString());
		return array.toString();
	}
	
	public static void renderText(final String content,HttpServletResponse response){
		try{
			response = initHeader(response);
			response.getWriter().write(content);
			response.getWriter().close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	private static HttpServletResponse initHeader(HttpServletResponse response){
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("UTF-8");
		return response;
	}
}
