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
  	
    <table border="1" bordercolor="block">
    	<tr>
    		<th>groupName</th>
    		<th>groupNumber</th>
    		<th>请求</th>
    	</tr>
   	<s:iterator var="groups" value="allGroupRequests">
   		<tr>
	   		<td><s:property value="key.name"/></td>
	   		<td><s:property value="key.number"/></td>
	   		<td>
	   			<table border="1" bordercolor="red">
	   				<tr>
	   					<th>username</th>
	   					<th>useremail</th>
	   					<th>操作</th>
	   				</tr>
	   			<s:iterator var="request" value="value">
	   				<tr><s:hidden value="#request.id"></s:hidden>
	   					<td><s:property value="#request.user.name"/></td>
	   					<td><s:property value="#request.user.email"/></td>
	   					<td>
	   						<s:a href="groupRequest_dealGroupRequest.action?id=%{#request.id}&resultStr=agree">agree</s:a>
	   						&nbsp;&nbsp;
	   						<s:a href="groupRequest_dealGroupRequest.action?id=%{#request.id}&resultStr=deny">deny</s:a>
	   					</td>
	   				</tr>
	   			</s:iterator>
	   			</table>
	   		</td>
   		</tr>
   	</s:iterator>
    </table>
  </body>
</html>
