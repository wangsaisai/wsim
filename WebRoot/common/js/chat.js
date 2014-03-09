//注：窗口id命名为正在通信的好友的      ( id + "_friend" )
//	群通信窗口命名   ( id + "_group" )

var group = new Ext.WindowGroup();

var websocket;

//var userId = document.getElementById("userId").value();

//用于展示用户的聊天信息
Ext.define('MessageContainer', {

	extend : 'Ext.view.View',
	
	autoScroll : true,

	trackOver : true,

	multiSelect : false,

	itemCls : 'l-im-message',

	itemSelector : 'div.l-im-message',

	overItemCls : 'l-im-message-over',

	selectedItemCls : 'l-im-message-selected',

	style : {
		overflow : 'auto',
		backgroundColor : '#fff'
	},

	tpl : [
			'<div class="l-im-message-warn">​交谈中请勿轻信汇款、中奖信息。 请遵守相关法律法规。</div>',
			'<tpl for=".">',
			'<div class="l-im-message">',
			'<div class="l-im-message-header l-im-message-header-{source}">{sender}  {time}</div>',
			'<div class="l-im-message-body">{content}</div>', '</div>',
			'</tpl>'],

	messages : [],

	initComponent : function() {
		var me = this;
		me.messageModel = Ext.define('Leetop.im.MessageModel', {
					extend : 'Ext.data.Model',
					fields : ['type', 'sender', 'receiver', 'content', 'time', 'source']
				});
		me.store = Ext.create('Ext.data.Store', {
					model : 'Leetop.im.MessageModel',
					data : me.messages
				});
		me.callParent();
	},

	//将服务器推送的信息展示到页面中
	receive : function(message) {
		var me = this;
		message['time'] = Ext.Date.format(new Date(message['time']),
				'H:i:s');
		if(message.sender == userId){
			message.source = 'self';
		}else{
			message.source = 'remote';
		}
		me.store.add(message);
		if (me.el.dom) {
			me.el.dom.scrollTop = me.el.dom.scrollHeight;
		}
	}
});


Ext.onReady(function() {

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
					if (message.type == 'friendMessage') {
						dealFriendMessage(message);
					} else if(message.type == "groupMessage") {
						dealGroupMessage(message);
					} else if(message.type == 'notify') {
						notify();
					} else if(message.type == 'friendRequest') {
						dealFriendRequest(message);
					} else if(message.type == 'videoRequest') {
						dealVideoRequest(message);
					}
				}
			};

});


function createFriendChatWindow(receiver) {
			var winId = receiver + "_friend";
			
			if(Ext.getCmp(winId) != null) {
				Ext.getCmp(winId).show();
				return;
			} 
	
			//创建用户输入框
			var input = Ext.create('Ext.form.field.HtmlEditor', {
						region : 'south',
						height : 120,
						enableFont : false,
						enableSourceEdit : false,
						enableAlignments : false,
						listeners : {
							initialize : function() {
								Ext.EventManager.on(me.input.getDoc(), {
											keyup : function(e) {
												if (e.ctrlKey === true
														&& e.keyCode == 13) {
													e.preventDefault();
													e.stopPropagation();
													send();
												}
											}
										});
							}
						}
					});

			//创建消息展示容器
			var output = Ext.create('MessageContainer', {
						region : 'center'
					});

			var dialog = Ext.create('Ext.panel.Panel', {
						region : 'center',
						layout : 'border',
						items : [output, input],
						buttons : [{
									text : '发送',
									handler : send
								}]
					});

			//展示窗口
			var win = Ext.create('Ext.window.Window', {
						id : winId,
						title : '与&nbsp;' + receiver + '&nbsp;聊天中',
						layout : 'border',
						iconCls : 'user-win',
						minWidth : 450,
						minHeight : 460,
						width : 450,
						animateTarget : 'websocket_button',
						height : 460,
						items : [dialog],
						border : false,
						manager : group,
						closeAction :'hide'
					});

			group.register(win);
			win.show();
			

			//发送消息
			function send() {
//				alert(receiver);
				var message = {};
				if (websocket != null) {
					if (input.getValue()) {
						Ext.apply(message, {
									type : "friendMessage",
									sender : userId+"",
									receiver : receiver+"",
									content : input.getValue(),
									time : new Date().getTime()
								});
						websocket.send(JSON.stringify(message));
						output.receive(message);
						input.setValue('');
					}
				} else {
					Ext.Msg.alert('提示', '您已经掉线，无法发送消息!');
				}
			}
				
//			function receive(message) {
//				output.receive(message);
//			}	
}


function createGroupChatWindow(groupId) {
	
			var winId = groupId + "_group";
			
			if(Ext.getCmp(winId) != null) {
				Ext.getCmp(winId).show();
				return;
			} 

	
			//创建用户输入框
			var input = Ext.create('Ext.form.field.HtmlEditor', {
						region : 'south',
						height : 120,
						enableFont : false,
						enableSourceEdit : false,
						enableAlignments : false,
						listeners : {
							initialize : function() {
								Ext.EventManager.on(me.input.getDoc(), {
											keyup : function(e) {
												if (e.ctrlKey === true
														&& e.keyCode == 13) {
													e.preventDefault();
													e.stopPropagation();
													send();
												}
											}
										});
							}
						}
					});

			//创建消息展示容器
			var output = Ext.create('MessageContainer', {
						region : 'center'
					});

			var dialog = Ext.create('Ext.panel.Panel', {
						region : 'center',
						layout : 'border',
						items : [output, input],
						buttons : [{
									text : '发送',
									handler : send
								}]
					});

			//展示窗口
			var win = Ext.create('Ext.window.Window', {
						id : winId,
						title : '群&nbsp;' + groupId + '&nbsp;聊天中',
						layout : 'border',
						iconCls : 'user-win',
						minWidth : 450,
						minHeight : 460,
						width : 450,
						animateTarget : 'websocket_button',
						height : 460,
						items : [dialog],
						border : false,
						manager : group,
						closeAction :'hide'
					});

			group.register(win);
			win.show();
			

			//发送消息
			function send() {
				var message = {};
				if (websocket != null) {
					if (input.getValue()) {
						Ext.apply(message, {
									type : "groupMessage",
									sender : userId+"",
									group : groupId+"",
									content : input.getValue(),
									time : new Date().getTime()
								});
						websocket.send(JSON.stringify(message));
//						output.receive(message);
						input.setValue('');
					}
				} else {
					Ext.Msg.alert('提示', '您已经掉线，无法发送消息!');
				}
			}
}



function dealFriendRequest(message) {
	alert("friendRequest: " + message.type + message.requester + message.remark);
}

function dealVideoRequest(message) {
	alert("视频请求： 请求者：" + message.requester);
	window.open("http://127.0.0.1:8080/wsim/conn.servlet?type=res&self=" + userId + "&other=" + message.requester
			, "_blank");
}


function dealFriendMessage(message) {
	var sender = message.sender;
	var winId = sender + "_friend";

	if(Ext.getCmp(winId) == null) {
		createFriendChatWindow(sender);
	} else {
		Ext.getCmp(winId).show();
	}
	
	var winn = Ext.getCmp(winId).items;
	var dialog = winn.first().items;
	var output = dialog.first();
	output.receive(message);

}


function dealGroupMessage(message) {
	var groupId = message.group;
	var winId = groupId + "_group";

	if(Ext.getCmp(winId) == null) {
		createGroupChatWindow(groupId);
	} else {
		Ext.getCmp(winId).show();
	}
	
	var winn = Ext.getCmp(winId).items;
	var dialog = winn.first().items;
	var output = dialog.first();
	output.receive(message);
	
}