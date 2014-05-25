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
    <table style="BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 0px" borderColor=#000000 bgColor=#bbbbff cellSpacing=0 cellPadding=0 width=250 align=center border=1>
    	<tr>
    		<th>id</th>
    		<th>Email</th>
    		<th>姓名</th>
    		<th>操作</th>
    	</tr>
   	<s:iterator value="users"  status="sta">
   		<tr>
   			<td  >${sta.index+1 }</td>
   			<td   height=40>${email }</td>
   			<td  height=40>${name }</td>
   			<td  height=40>
   				<s:if test="relations[#sta.index]==true">对方是你好友</s:if>
   				<s:else><a href="module/friend/friendRequest.jsp?responderId=${id}">加为好友</a></s:else>
   			</td>
   		</tr>
   	</s:iterator>
    </table>
  </body>
</html>
