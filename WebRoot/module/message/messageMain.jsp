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
 <div class="content" style="width:95%";>

  
  <div id="nav">
   <ul>
    <li>菜单</li>
  	
   </ul>

  </div>

  <div id="main">
    <table   border="0" cellspacing="0" cellpadding="0">
      <tr >
        <td align="left" id="left" >
          <iframe name="messageLeftFrame" width="100%" height="100%"src="/wsim/module/message/messageMainLeft.jsp" frameborder="0" scrolling="no"></iframe>
        </td>
       
      </tr>
    </table>
  </div>
 </div>
<div></div>
</body>
</html>
