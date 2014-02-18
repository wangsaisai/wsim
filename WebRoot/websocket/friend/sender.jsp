<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	
	<style type="text/css">
		div{
			margin-left: auto;
			margin-right: auto;
		}
	</style>
	
	<link rel="stylesheet" href="common/css/styles.css" type="text/css"></link>
	  
	<script type="text/javascript" src="common/js/validation.js"></script>
	
	<script type="text/javascript" src="common/js/friendWebSocket.js"></script>
	
  </head>
  
  
  <body onLoad="startWebSocket()">

<input type="hidden" id="sender" name="sender" value="1"/>
<input type="hidden" id="receiver" name="receiver" value="2"/>

<div onclick="say('d')"></div>

<div style="border: 1px solid #09F"></div>

<input type="text" id="writeMsg" />

<input type="button" value="send" onclick="sendMsg()" />


  </body>
</html>
