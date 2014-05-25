<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>wsim后台管理系统</title>

<link rel="stylesheet" href="/wsim/common/css/main.css" type="text/css"></link>

</head>
<body>
<div id="maskdiv" style="top:0px;left:0px;width:100%;height:100%;position:fixed;opacity:0.15;z-index:60000;background-color:rgb(0,0,0);display:none;">
 </div>
 <div class="content">

  <div id="header">
  
   <h1 STYLE="font-size:xx-large; color:#969D98;">
		WISM后台管理系统
   </h1>
   <div id="personal"> 角色：管理员 | <a href="javascript:void(0);" onclick="showdiv()">修改密码</a> | <a href="javascript:void(0);" onclick="showdiv1()" >添加管理员</a> | <a href="admin/admin_adminLogout">退出</a></div>
  </div>

  <div id="nav">
   <ul>
    <li><a href="/wsim/module/admin/mainLeft.jsp" target="leftFrame">菜单</a></li>
  	
   </ul>

  </div>

  <div id="main">
  
    <table width="90%" height="100%" border="0" cellspacing="0" cellpadding="0"  >
      <tr height="100%">
      
        <td align="left" id="left" height="520px">
          <iframe name="leftFrame" width="100%" height="100%" src="/wsim/module/admin/mainLeft.jsp" frameborder="0" scrolling="no" ></iframe>
        </td>
        <td align="left" id="right" height="520px">
          <iframe name="mainFrame" width="100%" height="100%" src="/wsim/module/admin/welcome.jsp" frameborder="0" scrolling="auto" ></iframe>
        </td>
      </tr>
    </table>
  </div>
 </div>
<div id="changepwd"style="display:none;"><div style="float:right;"onclick="closediv()" onmouseover="this.style.cursor='hand'">×</div><h3 style="background-color:#37BBFC;padding:5px;align:center;margin:0px;">修改密码</h3><iframe name="mainFrame" width="100%" height="100%" src="/wsim/module/admin/changePassword.jsp" frameborder="0" scrolling="auto" ></iframe></div>
<div id="addadmin"style="display:none;"><div style="float:right;"onclick="closediv1()" onmouseover="this.style.cursor='hand'">×</div><h3 style="background-color:#37BBFC;padding:5px;align:center;margin:0px;">添加管理员</h3><iframe name="mainFrame" width="100%" height="100%" src="/wsim/module/admin/addAdmin.jsp" frameborder="0" scrolling="auto" ></iframe></div>
<script type="text/javascript">

function showdiv(){
document.getElementById('changepwd').style.display='';
document.getElementById('maskdiv').style.display='';

}
function closediv(){
document.getElementById('changepwd').style.display='none';
document.getElementById('maskdiv').style.display='none';
}
function showdiv1(){
document.getElementById('addadmin').style.display='';
document.getElementById('maskdiv').style.display='';

}
function closediv1(){
document.getElementById('addadmin').style.display='none';
document.getElementById('maskdiv').style.display='none';
}
</script>

</body>

</html>
