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
	
  </head>
  
  <body>
  
  <input type="hidden" id="userId" name="userId" value="${userId }"/>
  
    <table style="BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 0px" borderColor=#000000 bgColor=#bbbbff cellSpacing=0 cellPadding=0 align=center border=1 >
    	<tr>
    		
    		<th>name</th>
    		<th>操作</th>
    		<th>friends</th>
    	</tr>
   	<s:iterator value="friendGroups"  status="sta">
   		<tr>
   			
   			<td height=40>${name }</td>
   			<td height=40><a href="module/friend/changeFriendGroupName.jsp?id=${id }">修改名称</a></td>
   			<td height=40>
   				<table border="0" cellspacing="0">
   					<!-- <tr>
   						
   						<th>friendGroupId</th>
   						<th>friendUserName</th>
   						<th>remark</th>
   						<th>修改备注名</th>
   						
   						<th>删除好友</th>
   					</tr> -->
   					<s:iterator value="friends">
   						<tr>
   							
   							<%-- <td>${friendgroup.name }</td>
   							<td>${user.name }</td> --%>
   							<td height=30>
   								${user.name}
   								<s:if test="remark != null">(${remark })</s:if>
   								
   							</td>
   							<td height=30><a href="module/friend/changeFriendRemark.jsp?id=${id }">修改备注名</a></td>
   							
   							<td height=30><a href="friend_delFriend.action?id=${id }">删除</a></td>
   						</tr>
   					</s:iterator>
   				</table>
   			</td>
   		</tr>
   	</s:iterator>
    </table>
    
  </body>
</html>
