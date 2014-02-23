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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<base target="_blank" />
  </head>
  
  	<link rel="stylesheet" href="common/css/styles.css" type="text/css"></link>
	  
	<script type="text/javascript" src="common/js/jquery-2.1.0.min.js"></script>
  
  <body onload="TargetA">
  
    <h2>导航页面</h2><br><br>
    部分操作可能需要先登录
    <p>
    	
    <div style="width:200px; border:1px solid red; position: relative;">
    <h3>管理员模块</h3>
    	<li><a href="module/admin/addAdmin.jsp">添加管理员</a></li>
    	<li><a href="module/admin/adminLogin.jsp">管理员登录</a></li>
    	<li><a href="module/admin/changePassword.jsp">修改密码</a></li>
    	<li><a href="admin_listAdmin">显示所有管理员</a></li>
    	<li><a href="admin_adminLogout">退出登录</a></li>
    </div>	
    	
    <p>
    
    <div style="width:200px; border:1px solid red; position: relative;">
    <h3>用户模块</h3>
    	<ul>
    		<li><a href="module/user/login.jsp">登录</a></li>
    		<li><a href="module/user/register.jsp">注册</a></li>
    		<li><a href="module/user/searchUser.jsp">查找用户</a></li>
    		<li><a href="module/user/changeName.jsp">修改姓名</a></li>
    		<li><a href="module/user/changePassword.jsp">修改密码</a></li>
    		<li><a href="user_getUserStatus">修改默认登录状态</a></li>
    		<li><a href="user_logout">退出</a></li>
    	</ul>
    </div>
    	
    <p>
    
    <div  style="width:200px; border:1px solid red; position: relative; left: 250px; top: -380px;">
    <h3>好友模块</h3>
    	<li><a href="module/friend/addFriendGroup.jsp">添加好友分组</a></li>
    	<li><a href="module/friend/searchFriend.jsp">搜索好友</a></li>
    	<li><a href="displayFriends">显示所有好友</a></li>
    	<li><a href="friendRequest_displayUndealFriendRequest">未处理的好友请求</a></li>
    	<li><a href="module/user/searchUser.jsp">添加好友</a></li>
    </div>	
    	
    <p>
    
    <div style="width:200px; border:1px solid red; position: relative; left: 250px; top: -380px;">
    <h3>群组模块</h3>	
    	<li><a href="module/group/createGroup.jsp" target="_blank">创建群</a></li>
    	<li><a href="module/group/searchGroup.jsp" target="_blank">搜索群</a></li>
    	<li><a href="groupUser_listGroupOfUser" target="_blank">查看加入的所有群</a></li>
    	<li><a href="groupRequest_displayUndealGroupRequest" target="_blank">查看未处理的群请求信息</a></li>
    	<li><a href=""></a></li>
    </div>
    
    <div style="width:200px; border:1px solid red; position: relative; left: 500px; top: -700px;">
    <h3>自由聊天室模块</h3>
    	<li><a href="module/room/createRoom.jsp">创建聊天室</a></li>
    </div>
  </body>
</html>
