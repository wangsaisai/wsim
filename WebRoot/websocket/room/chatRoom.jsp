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
    
    <title></title>
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
	
	<script type="text/javascript" src="common/js/roomWebSocket.js"></script>  
  </head>
  
  <body  onLoad="startWebSocket()">
  
  <div>
              欢迎你，${name }
    <a href="room_quitRoom?id=${room.id }&name=${name }">退出</a>
  </div>
  
  <div>
  	<input type="hidden" id="sender" name="sender" value="${name }"/>
	<input type="hidden" id="roomId" name="roomId" value="${room.id }"/>
	
	<div onclick="say('d')"></div>
	
	<div style="border: 1px solid #09F"></div>
	
	<input type="text" id="writeMsg" />
	
	<input type="button" value="send" onclick="sendMsg()" />
  </div>
  
  </body>
</html>
