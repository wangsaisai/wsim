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
	  
	  <link rel="stylesheet" href="common/css/message.css" type="text/css"></link>
	  
	  <script type="text/javascript" src="common/js/validation.js"></script>

<script>
	function getHref(){
		var pageNum = document.getElementById("pageNum").value;
		if(pageNum > ${page.totalPage} || pageNum < 1){
			alert("不存在第"+pageNum+"页");
			document.getElementById("pageNum").focus();
			return false;
		}
		document.getElementById("tiaozhuan").href = 'message_searchMessageByTime.action?currentPage='+pageNum + '${msg}';
	}
</script>

<script type="text/javascript" src="common/js/message.js"></script>
  
  </head>
  
  <body>
  
  <input type="hidden" name="beginTime" id="beginTime" value="<s:date name='beginTime' format='yyyy-MM-dd' />" />
  <input type="hidden" name="endTime" id="endTime" value="<s:date name='endTime' format='yyyy-MM-dd' />" />
  <input type="hidden" name="otherId" id="otherId" value="${otherId }" />
    
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td height="24" bgcolor="#353c44">
		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td>
			            <table width="100%" border="0" cellspacing="0" cellpadding="0">
			              <tr>
			                <td width="100%" valign="bottom"><span class="STYLE1">消息记录查询结果</span></td>
			              </tr>
			            </table>
		            </td>
		            <td></td>
		          </tr>
		        </table>
	        </td>
	      </tr>
	    </table>
    </td>
  </tr>
  <tr>
    <td>
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
	      <tr>
	        <td width="20%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">sender</span></div></td>
	        <td width="20%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">time</span></div></td>
	        <td width="60%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">content</span></div></td>
	      </tr>
	      <s:iterator value="messages">
	      <tr>
	        <td height="25" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${sender.name }</div></td>
	        <td height="25" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${time }</div></td>
	        <td height="25" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${content }</div></td>
	      </tr>
	      </s:iterator>
	    </table>
    </td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;当前第<strong>${page.currentPage}</strong> 页，共 <strong>${page.totalPage}</strong> 页，每页显示<strong>${page.everyPage}</strong>条记录</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td width="49"><div align="center"><a href="message_searchMessageByTime.action?currentPage=1${msg}"><img src="common/img/main_54.gif" width="40" height="15"/></a></div></td>
            <s:if test="page.hasPrePage">
            	<td width="49"><div align="center"><a href="message_searchMessageByTime.action?currentPage=${page.currentPage - 1 }${msg}"><img src="common/img/main_56.gif" width="45" height="15" /></a></div></td>
            </s:if>
            <s:else>
            	<td width="49"><div align="center"><img src="common/img/main_56.gif" width="45" height="15" /></div></td>
            </s:else>
            <s:if test="page.hasNextPage">
            	<td width="49"><div align="center"><a href="message_searchMessageByTime.action?currentPage=${page.currentPage + 1 }${msg}"><img src="common/img/main_58.gif" width="45" height="15"/></a></div></td>
            </s:if>
            <s:else>
            	<td width="49"><div align="center"><img src="common/img/main_58.gif" width="45" height="15" /></div></td>
            </s:else>
            <td width="49"><div align="center"><a href="message_searchMessageByTime.action?currentPage=${page.totalPage }${msg}"><img src="common/img/main_60.gif" width="40" height="15" /></a></div></td>
            <td width="37" class="STYLE22"><div align="center">转到</div></td>
            <td width="22"><div align="center">
              <input type="text" name="pageNum" id="pageNum"  style="width:25px; height:16px; font-size:12px; border:solid 1px #7aaebd;" onkeyup="value=value.replace(/[^0-9]/g,'');"/>
            </div></td>
            <td width="22" class="STYLE22"><div align="center">页</div></td>
            <td width="35"><a  onclick="getHref()" id="tiaozhuan"><img src="common/img/main_62.gif" width="26" height="15" /></a></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>    
    
    
    
    
  </body>
</html>
