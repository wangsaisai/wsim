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
		var serverIP = "<%=serverIP %>";
	</script>
</head>

<body>
	<h1>聊天室</h1>
	
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
