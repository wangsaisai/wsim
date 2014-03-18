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

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
<link rel="stylesheet" href="/wsim/common/css/mainLeft.css" type="text/css"></link>
	
	</head>

	<body>
		<table align="right">
			
			<tr>
				<td>
					<a href="module/user/searchUser.jsp" target="userMainFrame">查找用户</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="module/user/changeName.jsp" target="userMainFrame">修改姓名</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<a href="module/user/changePassword.jsp" target="userMainFrame">修改密码</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="user_getUserStatus" target="userMainFrame">修改默认登录状态</a>
				</td>
			</tr>
			
		</table>



	</body>
</html>
