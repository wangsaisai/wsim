<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>下拉框select demo</title>
</head>
<style type="text/css">
* {
    padding:0;
    margin:0
}
body {
    font-size:12px
}
.select_box {
    background: url("images/select_bg.gif") no-repeat scroll left top transparent;
    color: #444444;
    cursor: pointer;
    float: left;
    position: relative;
    width: 98px;
}
.select_txt {
    cursor: pointer;
    display: inline-block;
    height: 24px;
    line-height: 24px;
    overflow: hidden;
    padding-left: 10px;
    width: 88px;
}
.option {
    border: 1px solid #EDE7D6;
    display: none;
    left: -1px;
    overflow: hidden;
    position: absolute;
    top: 24px;
    width: 98px;
    z-index: 2;
}
.option a {
    background: none repeat scroll 0 0 #FFFFFF;
    display: block;
    height: 26px;
    line-height: 26px;
    padding: 0 10px;
    text-align: left;
    width: 100%;
}
</style>
<body>
<div class="select_box"><span class="select_txt">所在地区</span>
  <div class="option"><a>生产加工</a><a>经销批发</a><a>招商代理</a><a>商业服务</a><a>其他</a></div>
</div>
</body>
<script type="text/javascript" src="common/js/jquery-2.1.0.min.js"></script>

<script type="text/javascript">
//select下拉框
$(document).ready(function(){
$(".select_box").click(function(event){   
event.stopPropagation();
$(this).find(".option").toggle();
$(this).parent().siblings().find(".option").hide();
});
$(document).click(function(event){
var eo=$(event.target);
if($(".select_box").is(":visible") && eo.attr("class")!="option" && !eo.parent(".option").length)
$('.option').hide();       
});
/*赋值给文本框*/
$(".option a").click(function(){
var value=$(this).text();
$(this).parent().siblings(".select_txt").text(value);
$("#select_value").val(value)
 })
})
</script>
</html>