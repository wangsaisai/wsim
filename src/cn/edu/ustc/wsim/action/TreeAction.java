package cn.edu.ustc.wsim.action;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
  
public class TreeAction extends ActionSupport {  
 
	private String result;
	
	@Override
	public String execute() {
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
		setResult(array.toString());
		
		
		
		
		
		
		
		return SUCCESS;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}  