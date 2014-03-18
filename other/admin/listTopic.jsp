<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<link rel="stylesheet" href="css/adminStyle.css" type="text/css"></link>
<script>
	function getHref(){
		var pageNum = document.getElementById("pageNum").value;
		if(pageNum > ${page.totalPage} || pageNum < 1){
			alert("不存在第"+pageNum+"页");
			document.getElementById("pageNum").focus();
			return false;
		}
		document.getElementById("tiaozhuan").href = 'admin_listTopic.action?currentPage='+pageNum;
	}
</script>

<script type="text/javascript" src="js/admin.js"></script>

</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                
                <td width="100%" valign="bottom"><span class="STYLE1">话题基本信息列表</span></td>
              </tr>
            </table></td>
            <td></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
      <tr>
        <td width="25%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">ID</span></div></td>
        <td width="50%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">话题名</span></div></td>
      	<td width="25%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">删除</span></div></td>
      </tr>
      <s:iterator value="topics">
      <tr>
        <td height="25" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${id }</span></div></td>
        <td height="25" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${name }</div></td>
        <td height="25" bgcolor="#FFFFFF" class="STYLE19"><div align="center" class="STYLE21"><s:a href="admin_delTopic?topicId=%{id}&currentPage=%{page.currentPage}" onclick="return confirm('确定删除吗？')">删除</s:a></div></td>
      </tr>
      </s:iterator>
    </table></td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;当前第<strong>${page.currentPage}</strong> 页，共 <strong>${page.totalPage}</strong> 页，每页显示<strong>${page.everyPage}</strong>条记录</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
          
            <td width="49"><div align="center"><a href="admin_listTopic.action?currentPage=1"><img src="images/main_54.gif" width="40" height="15"/></a></div></td>
            <s:if test="page.hasPrePage">
            	<td width="49"><div align="center"><a href="admin_listTopic.action?currentPage=${page.currentPage - 1 }"><img src="images/main_56.gif" width="45" height="15" /></a></div></td>
            </s:if>
            <s:else>
            	<td width="49"><div align="center"><img src="images/main_56.gif" width="45" height="15" /></div></td>
            </s:else>
            <s:if test="page.hasNextPage">
            	<td width="49"><div align="center"><a href="admin_listTopic.action?currentPage=${page.currentPage + 1 }"><img src="images/main_58.gif" width="45" height="15"/></a></div></td>
            </s:if>
            <s:else>
            	<td width="49"><div align="center"><img src="images/main_58.gif" width="45" height="15" /></div></td>
            </s:else>
            <td width="49"><div align="center"><a href="admin_listTopic.action?currentPage=${page.totalPage }"><img src="images/main_60.gif" width="40" height="15" /></a></div></td>
            <td width="37" class="STYLE22"><div align="center">转到</div></td>
            <td width="22"><div align="center">
              <input type="text" name="pageNum" id="pageNum"  style="width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;" onkeyup="value=value.replace(/[^0-9]/g,'');"/>
            </div></td>
            <td width="22" class="STYLE22"><div align="center">页</div></td>
            <td width="35"><a  onclick="getHref()" id="tiaozhuan"><img src="images/main_62.gif" width="26" height="15" /></a></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
  