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
					<a href="module/message/searchFrame.jsp" target="messageMainFrame">查询好友消息</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="module/groupMessage/searchFrame.jsp" target="messageMainFrame">查询群组消息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			
			
		</table>



	</body>
</html>
