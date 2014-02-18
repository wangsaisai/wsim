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
  	  <script type="text/javascript" src="common/js/jquery-2.1.0.min.js"></script>
  	  <script type="text/javascript" src="common/js/login.js"></script>
  	  <script type="text/javascript" src="common/js/userWebSocket.js"></script>
  
  <script type="text/javascript">
	  $(document).ready(function(){ 
		  $("#statusStr option[value = '<s:property value="user.status.value"/>']").attr("selected", true);
	  })
  </script>
  
  </head>
  <s:property value="user.status.value"/>
  <body onLoad="startWebSocket()">
    ${email }  
    <s:property value="user.status.value"/>
    
    <input type="hidden" name="userId" id="userId" value="${user.id }"/>
  
  <div>
  	<select id="statusStr" name="statusStr">
  		<option value="online">online</option>
  		<option value="invisible">invisible</option>
  		<option value="busy">busy</option>
  		<option value="donotDisturb">donotDisturb</option>
  	</select>
  </div>
  
  <span id="show"></span>
  
  </body>
</html>
