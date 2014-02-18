<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
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
	  
	  <script type="text/javascript" src="common/js/validation.js"></script>
  		
  		<s:head theme="xhtml" />
		<sd:head parseContent="true" extraLocales="UTF-8" />
  </head>
  
  <body>
    <form action="message_searchMessageByTime" name="searchMessage" id="searchMessage" method="post">
    	<input type="text" name="otherId" id="otherId" placeholder="otherId" required="true"/>
    	<sd:datetimepicker name="beginTime" id="beginTime" displayFormat="yyyy-MM-dd" language="UTF-8"></sd:datetimepicker>
    	<sd:datetimepicker name="endTime" id="endTime" displayFormat="yyyy-MM-dd" language="UTF-8"></sd:datetimepicker>
    	<input type="submit" value="search" />
    </form>
  </body>
</html>
