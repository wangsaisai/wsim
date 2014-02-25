<%@ page language="java" import="java.util.*,cn.edu.ustc.wsim.datastructure.GlobalFinal" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	String serverIP = GlobalFinal.getServerIP();
	String roomAddress = request.getScheme()+"://"+serverIP+":"+request.getServerPort()+path+"/"+"module/room/joinRoom.jsp";
%>

<html>
<head>
	<base href="<%=basePath%>">
	<title>WebSocket 聊天室</title>
	<!-- 引入CSS文件 -->
	<link rel="stylesheet" type="text/css" href="lib/ext4/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="lib/ext4/shared/example.css" />
	<link rel="stylesheet" type="text/css" href="common/css/websocket.css" />
	
	<!-- 映入Ext的JS开发包，及自己实现的webscoket. -->
	<script type="text/javascript" src="lib/ext4/ext-all-debug.js"></script>
	<script type="text/javascript" src="common/js/websocket.js"></script>
	<script type="text/javascript">
		var user = "${name }";
		var roomId = "${room.id }";
	</script>
</head>

<body>
	<h1>WebSocket聊天室</h1>
	<p>通过HTML5标准提供的API与Ext富客户端框架相结合起来，实现聊天室，有以下特点：</p>
	<ul class="feature-list" style="padding-left: 10px;">
		<li>实时获取数据，由服务器推送，实现即时通讯</li>
		<li>利用WebSocket完成数据通讯，区别于轮询，长连接等技术，节省服务器资源</li>
		<li>结合Ext进行页面展示</li>
		<li>用户上线下线通知</li>
	</ul>
	
	<br>
	
	<div>
	              欢迎你，${name }<a href="room_quitRoom?id=${room.id }&name=${name }">退出</a>
	    <br>
	               聊天室地址为：&nbsp;&nbsp;<%=roomAddress %>?roomId=${room.id }&nbsp;&nbsp;
	   	 您可复制此地址给他人，邀请他加入聊天室
	    <br><br>
  	</div>
  	
  	
  	<br><br>
	
	<div id="websocket_button"></div>
</body>
</html>
