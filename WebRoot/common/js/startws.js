
Ext.onReady(function() {
			
			var websocket;

			//初始话WebSocket
			
				if (window.WebSocket) {
					websocket = new WebSocket(encodeURI("ws://127.0.0.1:8080/wsim/user.ws?userId=" + userId));
					websocket.onopen = function() {
						//连接成功
						alert("open");
					}
					websocket.onerror = function() {
						//连接失败
						alert("error");
					}
					websocket.onclose = function() {
						//连接断开
//						alert("close");
					}
					//消息接收
					websocket.onmessage = function(message) {
						var message = JSON.parse(message.data);
						//接收用户发送的消息
						if (message.type == 'message') {
							output.receive(message);
						} else if(message.type == 'notify') {
							notify();
						} else if(message.type == 'friendRequest') {
							dealFriendRequestMessage(message);
						}
					}
				}
			;
		
});



function notify(){
	alert("notifyyyyyyyy");
}


function dealFriendRequestMessage(message) {
	alert("friendRequest: " + message.type + message.requester + message.remark);
}