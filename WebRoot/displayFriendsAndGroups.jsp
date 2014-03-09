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
	
	<!-- 引入CSS文件 -->
	<link rel="stylesheet" type="text/css" href="lib/ext4/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="lib/ext4/shared/example.css" />
	<link rel="stylesheet" type="text/css" href="common/css/websocket.css" />
	
	<!-- 映入Ext的JS开发包，及自己实现的webscoket. -->
	<script type="text/javascript" src="lib/ext4/ext-all-debug.js"></script>
	<script type="text/javascript" src="common/js/chat.js"></script>
	<script type="text/javascript">
		var userId = "${userId }";
	</script>
	  
  </head>
  
  <body>
    
    <h3>好友</h3>  
    <table border="1">
    	<tr>
    		<th>friendGroupId</th>
    		<th>name</th>
    		<th>friends</th>
    	</tr>
   	<s:iterator value="friendGroups"  status="sta">
   		<tr>
   			<td>${id }</td>
   			<td>${name }</td>
   			<td>
   				<table border="1">
   					<tr>
   						<th>friendId</th>
   						<th>friendGroupId</th>
   						<th>friendUserName</th>
   						<th>remark</th>
   						<th>文字通信</th>
   						<th>语音通信</th>
   					</tr>
   					<s:iterator value="friends">
   						<tr>
   							<td>${id }</td>
   							<td>${friendgroup.name }</td>
   							<td>${user.name }</td>
   							<td>${remark }</td>
   							<td>
   								<input type="button" onclick="createFriendChatWindow(${user.id })" value="文字通信"/>
   							</td>
   							<td><a href="conn.servlet?type=req&self=${userId }&other=${user.id }">语音视频</a></td>
   						</tr>
   					</s:iterator>
   				</table>
   			</td>
   		</tr>
   	</s:iterator>
    </table>
    
    <br>
    
    <h3>群</h3>
    <table border="1">
    	<tr>
    		<th>name</th>
    		<th>number</th>
    		<th>群名片</th>
    		<th>通信</th>
    	</tr>
   	<s:iterator value="groupUsers">
   		<tr>
   			<td>${group.name }</td>
   			<td>${group.number }</td>
   			<td>${remark }</td>
   			<td>
   				<input type="button"  onclick="createGroupChatWindow(${group.id })" value="文字通信"//>
   			</td>
   		</tr>
   	</s:iterator>
    </table>
    
    
      
    <div id="websocket_button"></div>
    
    
  </body>
</html>
