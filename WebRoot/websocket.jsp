<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	
	<style type="text/css">
		div{
			margin-left: auto;
			margin-right: auto;
		}
	</style>
	
  </head>
  
  
  <body onLoad="startWebSocket()">

<script type="text/javascript">
	var ws = null;
	
	function startWebSocket() {
		
		if('WebSocket' in window) {
			try {
				ws = new WebSocket("ws://127.0.0.1:8080/wsim/servlet/MyWebSocketServlet.servlet");
			} catch(e) {
				alert("1");
			}
		} else if('MozWebSocket' in window) {
			try {
				ws = new WebSocket("ws://127.0.0.1:8080/wsim/servlet/MyWebSocketServlet.servlet");
			} catch(e) {
				alert("1");
			}
		} else {
			alert("not support");
		}
		
		ws.onmessage = function(evt) {
			say(evt.data);
		}
		
		ws.onclose = function(evt) {
			alert("close");
		}
		
		ws.onopen = function(evt) {
			alert("open");
		}
		
	}
	
	
	function sendMsg() {
		ws.send(document.getElementById('writeMsg').value);
	}
	
	
	function say(msg) {
		var div = document.createElement("div");
		div.innerHTML = msg;
		document.body.appendChild(div);
	}
</script>

<div onclick="say('d')"></div>

<div style="border: 1px solid #09F"></div>

<input type="text" id="writeMsg" />

<input type="button" value="send" onclick="sendMsg()" />


  </body>
</html>
