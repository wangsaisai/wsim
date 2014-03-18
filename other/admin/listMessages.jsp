<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'userList.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" href="css/style.css" type="text/css"
			media="all"></link>
	</head>

	<body>
		<s:if test="messages.size == 0">
			<h3>恭喜您，您的消息阅读完毕</h3>
		</s:if>
		<s:if test="messages.size > 0">
		<h3>
			我的消息（共
			<s:property value="%{messages.size()}" />
			条）：
		</h3>
		<table>
			<tr>
				<td width="400px" align="center">接收时间</td>
				<td width="500px" align="center">内容</td>
				<td width="100px" align="center">阅读</td>
				<td width="100px" align="center">删除</td>
			</tr>
			<s:iterator value="messages">
				<tr >
					<td align="center">
						${time }
					</td>
					<td  align="center"><div style="width:500px; white-space:nowrap;overflow:hidden;text-overflow:ellipsis; border:0px solid #000">${content}</div></td>
					<td align="center"><s:a href="message!readMessage.action?id=%{id}">阅读</s:a></td>
					<td align="center"><s:a href="message!delMessage.action?id=%{id}" onclick="return confirm('确定删除吗？')">删除</s:a></td>
				</tr>
				
			</s:iterator>
		</table>
		</s:if>

	</body>
</html>
