<%@ page contentType="text/html; charset=GBK" language="java"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>magic知识问答社区</title>

<style type="text/css">

body {
    font-size:12px;
}

/* 可抽取出 global.css  START*/
html, body, div, span, object, iframe, pre, a, code, font, ul, li, img, samp, small, strong, big, form, label, table, caption, tr, td {
    margin:0px;
	padding:0px;
	border:0px;
	outline:0;
	font-weight:inherit;
	font-style:inherit;
	font-family:inherit;
	text-align:left;
	vertical-align:baseline;
}

.content { 
	clear:both;
	margin:0px auto 0px auto;
	text-align:left;
}
/* 可抽取出 global.css  END*/

.content {
	padding-left: 10px;
}

/* 可抽取出 layouts.css  START*/

/* header start*/
#header {
	width:100%;
	height:60px;
	position:relative;
}

#main_title {
	font-size: xx-large;
	color: #969D98;
	float: left;
	position:absolute;
	left:10px;
	top:10px;
}

#personal {
    position:absolute;
	right:10px;
	top:20px;
	width:auto;
}
/* header end*/

/* nav start*/
#nav {
    width:100%;
	height:40px;
	line-height:40px;
	background:#00ae9d;
	color:#fff;
	font-size:14px;
	font-weight:bold;
}
#nav li {
    list-style:none;
}
#nav a {
    color:#fff;
	border-right: 1px solid #fff; /* | menu1 | menu2 | border*/
	padding: 0 1em 0 0;
}
#nav ul li {
    float:left;
	padding:0px 5px;
	margin:0px 10px;
}
/* nav end*/

/* main start */
#main {
    width:100%;
    text-align: left;
    height:100%;
}
#left {
	width:190px;
	text-align:left;
	
	vertical-align:top;
	padding-top:15px;
}
#right {
    background:#fff;
	vertical-align:top;
}
/* main end */

/* 可抽取出 layouts.css  END*/

</style>

</head>
<body>
 <div class="content">

  <div id="header">
  
   <span id="main_title">
		magic知识问答社区管理系统
   </span>
   <div id="personal"> 角色：管理员 | <a href="user!logout">退出</a></div>
  </div>

  <div id="nav">
   <ul>
    <li><a href="admin/mainLeft.jsp" target="leftFrame">菜单</a></li>
  	
   </ul>

  </div>

  <div id="main">
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="100%">
        <td align="left" id="left" height="520px">
          <iframe name="leftFrame" width="100%" height="100%" src="admin/mainLeft.jsp" frameborder="0" scrolling="no"></iframe>
        </td>
        <td align="left" id="right" height="520px">
          <iframe name="mainFrame" width="100%" height="100%" src="admin_listUser" frameborder="0" scrolling="auto"></iframe>
        </td>
      </tr>
    </table>
  </div>
 </div>
<div></div>
</body>
</html>
