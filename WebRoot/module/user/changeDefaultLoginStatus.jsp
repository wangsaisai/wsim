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
	  <script type="text/javascript" src="common/js/jquery-2.1.0.min.js"></script>
  
  <script type="text/javascript">
	  $(document).ready(function(){ 
		  $("#statusStr option[value = '<s:property value="status"/>']").attr("selected", true);
	  })
  </script>
  
  </head>
  
  <body>
    <form action="user_changeStatus" id="changeStatus" name="changeStatus" method="post">
    	<table>
  			<tr>
  				<th>修改 默认登录状态</th>
  			</tr>
  			<tr>
  				<td>
  					<select id="statusStr" name="statusStr">
				  		<option value="ONLINE">online</option>
				  		<option value="INVISIBLE">invisible</option>
				  		<option value="BUSY">busy</option>
				  		<option value="DONOTDISTURB">donotDisturb</option>
				  	</select>
  				</td>
  			</tr>
  			<tr>
  				<td><input type="submit" id="submit" name="submit" value="修改"/></td>
  			</tr>
  		</table>
    </form>
  </body>
</html>
