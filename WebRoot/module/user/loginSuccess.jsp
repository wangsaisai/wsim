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
  	  <script type="text/javascript" src="common/js/changeStatusTemp.js"></script>
  
  <script type="text/javascript">
	  $(document).ready(function(){ 
		  $("#statusStr option[value = '<s:property value="user.status"/>']").attr("selected", true);
	  })
  </script>
  
  </head>
  
  
  
  <body>
  
  <p>登录成功.welcome,${user.name }, &nbsp; &nbsp;${email } </p><p>
  
     
      当前状态：<s:property value="user.status"/>
    
    <input type="hidden" name="userId" id="userId" value="${user.id }"/>
  
  <div>
  	<select id="statusStr" name="statusStr">
  		<option value="ONLINE">online</option>
  		<option value="INVISIBLE">invisible</option>
  		<option value="BUSY">busy</option>
  		<option value="DONOTDISTURB">donotDisturb</option>
  	</select>
  </div>
  
  <p>
  状态修改结果：<span id="show"></span>
  
  </body>
</html>
