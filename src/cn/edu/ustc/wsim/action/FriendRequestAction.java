package cn.edu.ustc.wsim.action;

import java.util.List;

import cn.edu.ustc.wsim.bean.FriendRequest;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.datastructure.OnlineUsers;
import cn.edu.ustc.wsim.enumerates.FriendRequestResult;
import cn.edu.ustc.wsim.service.FriendRequestService;
import cn.edu.ustc.wsim.service.FriendService;
import cn.edu.ustc.wsim.service.UserService;
import cn.edu.ustc.wsim.websocket.user.UserMessageInboundPool;

import com.opensymphony.xwork2.ActionSupport;

public class FriendRequestAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8078247492964830678L;
	private Integer id;
	private Integer requesterId;
	private Integer responderId;
	private FriendRequestResult result;
	private String remark;
	
	private String resultStr;
	
	private List<FriendRequest> friendRequests;
	
	private FriendRequestService friendRequestService;
	
	private UserService userService;
	private FriendService friendService;
	
	private String errorMsg;
	
	
	public String addFriendRequest() {
		
		User requester = friendRequestService.getLoginUser();
		User responder = new User(responderId);
		
		if(userService.isFriend(requester, responder)) {
			errorMsg = "对方已经是您的好友";
			return "addError";
		}
		
		//判断黑名单，赞省略
		
		//封装请求信息
		FriendRequest friendRequest = new FriendRequest();
		friendRequest.setRequester(requester);
		friendRequest.setResponder(responder);
		friendRequest.setResult(FriendRequestResult.UNDEAL);
		friendRequest.setRemark(remark);
		
		if(friendRequestService.add(friendRequest)) {
			//如果该用户在线，则通知该用户
			//将id信息添加到newFriendRequest对象中
			FriendRequest newFriendRequest = friendRequestService.get(requester, responder);
			if(OnlineUsers.isLogin(responderId)) {
				UserMessageInboundPool.sendFriendRequestMessage(newFriendRequest);
			}
			return "addSuccess";
		}
		else {
			errorMsg = "add FriendRequest error";
			return "addError";
		}
				
	}
	
	
	public String displayUndealFriendRequest() {
		this.setFriendRequests(friendRequestService.getUndealFriendRequests(friendRequestService.getLoginUser()));
		return "listUndealFriendRequest";
	}
	
	
	public String delFriendRequest() {
		int intId = id;
		if(friendRequestService.del(intId))
			return "delSuccess";
		else {
			errorMsg = "del error";
			return "delError";
		}
	}
	
	//deal 改为 handle 比较合适， 防止与del混淆
	public String dealFriendRequest() {
		
		switch(resultStr) {
		case "agree" : result = FriendRequestResult.AGREE; break;
		case "deny" : result = FriendRequestResult.DENY; break;
		case "agreeAndRequest" : result = FriendRequestResult.AGREEANDADD; break;
		default : break;
		}
		
		FriendRequest friendRequest = (FriendRequest) friendRequestService.get(id);
		friendRequest.setResult(result);
		if(friendRequestService.update(friendRequest)) {
			
			User requester = friendRequest.getRequester();
			User receiver = friendRequest.getResponder();
			
			switch (result) {
			case AGREE:		//接受请求
				friendService.addFriend(requester, receiver);
				break;
			case DENY:	//拒绝请求
				break;
			case AGREEANDADD:	//用户同意好友请求，并添加对方为好友
				friendService.addFriend(requester, receiver);
				friendService.addFriend(receiver, requester);
				break;

			default:
				break;
			}
			
			return "dealFriendRequestSuccess";
		} else {
			errorMsg = "update Error";
			return "dealFriendRequestError";
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Integer requesterId) {
		this.requesterId = requesterId;
	}

	public Integer getResponderId() {
		return responderId;
	}

	public void setResponderId(Integer responderId) {
		this.responderId = responderId;
	}

	public FriendRequestResult getResult() {
		return result;
	}

	public void setResult(FriendRequestResult result) {
		this.result = result;
	}

	public FriendRequestService getFriendRequestService() {
		return friendRequestService;
	}

	public void setFriendRequestService(FriendRequestService friendRequestService) {
		this.friendRequestService = friendRequestService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public List<FriendRequest> getFriendRequests() {
		return friendRequests;
	}


	public void setFriendRequests(List<FriendRequest> friendRequests) {
		this.friendRequests = friendRequests;
	}


	public String getResultStr() {
		return resultStr;
	}


	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

}
