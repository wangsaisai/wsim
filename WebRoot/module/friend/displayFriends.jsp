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
	
	  <link rel="stylesheet" href="../common/css/styles.css" type="text/css"></link>
	  
	  <script type="text/javascript" src="../common/js/validation.js"></script>
  
  </head>
  
  <body>
    <table border="1">
    	<tr>
    		<th>friendGroupId</th>
    		<th>name</th>
    		<th>操作</th>
    		<th>friends</th>
    	</tr>
   	<s:iterator value="friendGroups"  status="sta">
   		<tr>
   			<td>${id }</td>
   			<td>${name }</td>
   			<td><a href="module/friend/changeFriendGroupName.jsp?id=${id }">修改名称</a></td>
   			<td>
   				<table border="1">
   					<tr>
   						<th>friendId</th>
   						<th>friendGroupId</th>
   						<th>friendUserId</th>
   						<th>remark</th>
   						<th>修改备注名</th>
   						<th>修改所在分组</th>
   						<th>删除好友</th>
   					</tr>
   					<s:iterator value="friends">
   						<tr>
   							<td>${id }</td>
   							<td>${friendgroup.name }</td>
   							<td>${user.name }</td>
   							<td>${remark }</td>
   							<td><a href="module/friend/changeFriendRemark.jsp?id=${id }">修改备注名</a></td>
   							<td><a>change friend group(unfinish)</a></td>
   							<td><a href="friend_delFriend.action?id=${id }">删除</a></td>
   						</tr>
   					</s:iterator>
   				</table>
   			</td>
   		</tr>
   	</s:iterator>
    </table>
  </body>
</html>
