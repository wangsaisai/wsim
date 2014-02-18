package cn.edu.ustc.wsim.bean;

import cn.edu.ustc.wsim.enumerates.FriendRequestResult;


/**
 * Friendrequest entity. @author MyEclipse Persistence Tools
 */

public class FriendRequest implements java.io.Serializable {

	//枚举，标识好友请求结果
//	public enum FriendRequestResult {
//		UNDEAL,		//未处理
//		AGREE, 		//接受	
//		DENY,		//拒绝
//		AGREEANDREQUEST		//同意并添加对方为好友
//	}
	
	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User requester;
	private User responder;
	private FriendRequestResult result;
	private String remark;

	// Constructors

	/** default constructor */
	public FriendRequest() {
	}
	
	public FriendRequest(Integer id) {
		this.id = id;
	}
	
	/** full constructor */
	public FriendRequest(Integer id, User requester,
			User responder, FriendRequestResult result, String remark) {
		this.id = id;
		this.requester = requester;
		this.responder = responder;
		this.result = result;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getRequester() {
		return this.requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public User getResponder() {
		return this.responder;
	}

	public void setResponder(User responder) {
		this.responder = responder;
	}

	public FriendRequestResult getResult() {
		return this.result;
	}

	public void setResult(FriendRequestResult result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}