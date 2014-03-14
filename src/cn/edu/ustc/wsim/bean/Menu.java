package cn.edu.ustc.wsim.bean;

public class Menu {
	
	private String text;
	private String ids;
	private String cls;
	private Boolean leaf;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	
	

}
