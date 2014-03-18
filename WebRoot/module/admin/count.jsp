<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" href="css/style.css" type="text/css"
			media="all"></link>
	</head>

	<body>
		<h3>wsim数据统计：</h3>
		<br/>
		<h4>总用户数：${countUser }</h4>
		
		<h4>总群数：${countGroup }</h4>
		
		<h4>总消息数：${countMessage }&nbsp;&nbsp;&nbsp;&nbsp;
			总群消息数：${countGroupMessage }&nbsp;&nbsp;&nbsp;&nbsp;
			共计：${countAllMessage }
		</h4>
		
		<h4>在线用户数：${countOnlineUser }</h4>
		
		<h4>当前聊天室数：${countRooms }</h4>
		
		<br/>
	</body>
</html>
