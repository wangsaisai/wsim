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
	
	  <link rel="stylesheet" href="common/css/styles.css" type="text/css"></link>
	  
	  <script type="text/javascript" src="common/js/validation.js"></script>
  
  </head>
  
  <body>
  	您有${friendRequests.size() }条好友请求信息
    <table>
    	<tr>
    		<th>id</th>
    		<th>requester</th>
    		<th>操作</th>
    	</tr>
   	<s:iterator value="friendRequests"  status="sta">
   		<tr>
   			<td>${sta.index+1 }</td>
   			<td>${requester.name }</td>
   			<td>
   				<form action="friendRequest_dealFriendRequest" method="post">
   					<input type="hidden" name="id" value="${id }"/>
				   	<select id="resultStr" name="resultStr">
				  		<option value="agreeAndRequest">同意并添加对方为好友</option>
				  		<option value="agree">同意</option>
				  		<option value="deny">拒绝</option>
				  	</select>
   					<input type="submit" value="submit"/>
   				</form>
   			</td>
   		</tr>
   	</s:iterator>
    </table>
  </body>
</html>
