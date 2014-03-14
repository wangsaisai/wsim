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
  	<form action="user2_findPassword" method="post" id="findPassword" name="findPassword">
  		<table>
  			<tr>
  				<th>请输入您的注册邮箱</th>
  			</tr>
  			<tr>
  				<td><input type="text" id="email" name="email" placeholder="Email" required="true"/></td>
  			</tr>
  			<tr>
  				<td><input type="submit" id="submit" name="submit" value="提交"/></td>
  			</tr>
  		</table>
  	</form>	
  <body>
    
  </body>
</html>
