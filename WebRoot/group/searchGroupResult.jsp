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
    <table>
    	<tr>
    		<th>id</th>
    		<th>name</th>
    		<th>number</th>
    		<th>操作</th>
    	</tr>
   	<s:iterator value="groups"  status="sta">
   		<tr>
   			<td>${sta.index+1 }</td>
   			<td>${name }</td>
   			<td>${number }</td>
   			<td>
   				<s:if test="relations[#sta.index]==true">你已加入该群</s:if>
   				<s:else><s:a href="groupRequest_addGroupRequest.action?groupId=%{id}">申请加入</s:a></s:else>
   			</td>
   		</tr>
   	</s:iterator>
    </table>
  </body>
</html>
