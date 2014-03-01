<%@ page language="java" import="java.util.*,cn.edu.ustc.wsim.enumerates.GroupRole" pageEncoding="utf-8"%>
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
	
  
  </head>
  
  <body>
  
    <table>
    	<tr>
    		<th>name</th>
    		<th>number</th>
    		<th>群名片</th>
    		<th>修改群信息</th>
    		<th>修改群名片</th>
    		<th>退出群|删除群</th>
    	</tr>
   	<s:iterator value="groupUsers">
   		<tr>
   			<td>${group.name }</td>
   			<td>${group.number }</td>
   			<td>${remark }</td>
   			<td align="center"><a href="module/group/updateGroup.jsp?groupId=${group.id }">修改</a></td>
   			<td><a href="module/group/updateGroupUserRemark.jsp?groupId=${group.id }">修改群名片</a></td>
   			<td align="center">
   				<s:if test="role.toString()==@cn.edu.ustc.wsim.enumerates.GroupRole@CREATER.toString()">
   					<a href="group_delGroup?id=${group.id }">删除群</a>
   				</s:if>
   				<s:else>
   					<a href="groupUser_quitGroup?id=${id }">退出群</a>
   				</s:else>
   			</td>
   		</tr>
   	</s:iterator>
    </table>
    
  </body>
</html>
