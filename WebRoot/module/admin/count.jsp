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
		<link rel="stylesheet" href="common/css/countstyle.css" type="text/css"
			media="all"></link>
	</head>

	<body>
		
		
		<table align=center border=0>
		<tr id='head'><TH></TH><th>WSIM数据统计</th><TH></TH></tr>
		<tr id='lightcolor'><td>总用户数：${countUser }</td><td></td><td></td></tr>
		<tr id='heavycolor'><td>总群数：${countGroup }</td><td></td><td></td></tr>
		<tr id='lightcolor'><td>总消息数：${countMessage }</td><td>总群消息数：${countGroupMessage }</td><td>共计：${countAllMessage }</td></tr>
		<tr id='heavycolor'><td>在线用户数：${countOnlineUser }</td><td></td><td></td></tr>
		<tr id='lightcolor'><td>当前聊天室数：${countRooms }</td><td></td><td></td></tr>
		
		</table>
	</body>
</html>
