<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
	<!--<![endif]-->
	<head>
		<meta charset="GBK">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>Login Form</title>
		<link rel="stylesheet" href="../css/login.css">
		<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

		<link rel="stylesheet" href="../css/email.css" type="text/css"></link>
<STYLE type="text/css">
body {
  font: 22px/40px "Lucida Grande", Tahoma, Verdana, sans-serif;
  color: #404040;
  background: white;
}
</STYLE>


		<script type="text/javascript" src="../js/jquery-1.9.0.min.js">
</script>
		<script type="text/javascript" src="../js/jquery.validate.js">
</script>
		<script type="text/javascript" src="../js/messages_zh.js">
</script>
		<script type="text/javascript" src="../js/jquery.cookie.js">
</script>
		<script type="text/javascript" src="../js/emailSuggest.js">
</script>
		<script type="text/javascript" src="../js/validate.js">
</script>

	
	</head>
	<body>
		<p>
					<font color="red"><span>${registerInfo }</span>
					</font>
				</p>
		<div class="addAdmin">
			<h1>
				添加管理员
			</h1>
			<form method="post" action="admin_addAdmin" id="register" name="register">
				
				<p>
					<input type="text" name="email" id="email" value=""
						placeholder="电子邮件">
					<span></span>
				</p>
				<p>
					<input type="text" name="username" value="" id="username"
						placeholder="用户名">
					<span></span>
				</p>
				<p>
					<input type="password" name="password" value="" id="password"
						placeholder="密码">
					<span></span>
				</p>
				<p>
					<input type="password" name="password2" value="" id="password2"
						placeholder="确认密码">
					<span></span>
				</p>
				<p class="submit">
					<input type="submit" name="commit" id="login_0" value="添加">
				</p>
			</form>
		</div>

		
		

	</body>
</html>
