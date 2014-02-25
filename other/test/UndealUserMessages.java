package cn.edu.ustc.wsim.websocket.user;

import java.util.List;

import cn.edu.ustc.wsim.bean.FriendRequest;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.service.FriendRequestService;


//not use ----------error
public class UndealUserMessages {
	
	private FriendRequestService friendRequestService;
	
	private static UndealUserMessages uum;
	
	public void init() {
		uum = this;
		uum.friendRequestService = this.friendRequestService;
	}
	
	
	public List<FriendRequest> getUndealFriendRequest(Integer userId) {
		if(friendRequestService == null)
			System.out.println("nnnnnnnull");
		return friendRequestService.getUndealFriendRequests(new User(userId));
	}

	
	public FriendRequestService getFriendRequestService() {
		return friendRequestService;
	}

	public void setFriendRequestService(FriendRequestService friendRequestService) {
		this.friendRequestService = friendRequestService;
	}

}
