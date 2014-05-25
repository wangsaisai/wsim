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
    
    <title>管理员登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	 <!--  <link rel="stylesheet" href="common/css/styles.css" type="text/css"></link> -->
	 <link rel="stylesheet" href="common/css/flexi-background.css" type="text/css" media="screen" />
     <link rel="stylesheet" href="common/css/adminstyles.css" type="text/css" media="screen" />
	  <script type="text/javascript" src="common/js/validation.js"></script>
  
  </head>
  
  <body>
  <script src="common/js/flexi-background.js" type="text/javascript" charset="utf-8"></script>
  <div id="box"> 
  <h1>Admin Login</h1>
  	<form action="admin/adminLogin" method="post" id="adminLogin" name="adminLogin">
  		
  				
  			<input type="text" id="email" name="email" placeholder="Email"/>
  			<input type="password" id="password" name="password" placeholder="password"/>
  			<input type="submit" id="submit" name="submit" value="Log in"/>
  			<input type="checkbox" id="remember" value="Remember" />
            <div class="hover-opacity">
            <label for="remember">Remember me</label>
            </div>
  		
  	</form>
  	</div>
  </body>
</html>
