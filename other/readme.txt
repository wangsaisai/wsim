注意 websocket 无法连接时的可能错误原因
ip地址
项目名



favicon.ico  网站logo



奇怪的bug
WebRoot目录下创建文件夹为static 和 statics时，里面的文件无法访问，报404错误
使用tomcat6时也是一样的错误




//////////////////////////////////////////////////////////////
websocket通信类型定义											//
															//
friendRequest												//
groupRequest												//		
friendMessage												//		
groupMessage												//
……															//
															//
----------------------------------							//
															//
friendMessage												//
	type													//	
	sender	-- id											//
	receiver	-- id										//
	content													//
	time													//	
															//
------------------------									//		
															//
groupMessage												//
	type													//
	sender   ---id											//
	group    ---id											//
	content													//	
	time													//	
															//
															//
//////////////////////////////////////////////////////////////





[
	{"type" : "friendMessage"},
	{"sender" : "发送者的id"},
	{"receiver" : "接收者的id"},
	{"content" : "发送的消息内容"},
	{"time" : new Date().getTime()}
]



[
	{"type" : "groupMessage"},
	{"sender" : "群id"},
	{"group" : "接收者的id"},
	{"content" : "发送的消息内容"},
	{"time" : new Date().getTime()}
]


