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
	  
	  <link rel="stylesheet" href="common/css/page.css" type="text/css"></link>
	  
	  <script type="text/javascript" src="common/js/validation.js"></script>
  
  </head>
  
  <body>
  
    
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
	      <tr>
	        <td width="20%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">id</span></div></td>
	        <td width="30%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">emai</span></div></td>
	        <td width="20%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">name</span></div></td>
	        <td width="30%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
	      </tr>
	      <tr>
	        <td height="25" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${user.id }</div></td>
	        <td height="25" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${user.email }</div></td>
	        <td height="25" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${user.name }</div></td>
	        <td height="25" bgcolor="#FFFFFF" class="STYLE19">
	        	<div align="center">
	        		<s:a href="admin/manager_delUser?userId=%{id}" onclick="return confirm('确定删除吗？')">删除</s:a>
	        	</div>
	        </td>
	      </tr>
	    </table>
    </td>
  </tr>
</table>    
    
    
    
    
  </body>
</html>
