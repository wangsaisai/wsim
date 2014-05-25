<%@ page language="java" import="java.util.*,cn.edu.ustc.wsim.datastructure.GlobalFinal" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	String serverIP = GlobalFinal.getServerIP();
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
	
	<!-- 引入CSS文件 -->
	<!-- <link rel="stylesheet" type="text/css" href="lib/ext4/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="lib/ext4/shared/example.css" /> -->
	
	<script type="text/javascript" src="lib/ext4/ext-all-debug.js"></script>
	<link rel="stylesheet" type="text/css" href="lib/ext4/shared/example.css" />
	<link rel="stylesheet" type="text/css" href="common/css/websocket.css" />
	<script type="text/javascript" src="common/js/chat.js"></script>
	
	
	
	<!-- 映入Ext的JS开发包，及自己实现的webscoket. -->
	<%-- <script type="text/javascript" src="lib/ext4/ext-all-debug.js"></script> --%>
	
	<script type="text/javascript">
		//var userId = "${user.id }";
		var serverIP = "<%=serverIP %>";
		var myName = "";
	</script>
	
	
	<script type="text/javascript" src="classes/system/Bootstrap.js"></script>
	
	
	
	
	<script>
	
		var startLoadingTime = new Date().getTime(),
			ctx = "",theme = "ext-all";
		Leetop.Bootstrap.loadCallback = function(){
			Ext.Loader.setConfig({
	    		enabled: true
	    	});
	        Ext.Loader.setPath({
	            "Ext" : "lib/ext4",
	            "Leetop" : "classes"
	        });
	        Ext.Loader.require(["Leetop.system.logger.LoggerFactory",
			                    "Leetop.desktop.Desktop",
			                    "Leetop.system.Console"
			                   ]);
	        
	        Ext.onReady(function () {
	        	Ext.MessageBox.show({
	                title : "正在加载桌面, 请稍候...",
	                msg: "<br/>加载系统基础组件....",
	                progressText: "20%",
	                width:300,
	                progress:true,
	                closable:false,
	                icon:"ext-mb-download"
	            });
	        	Ext.MessageBox.updateProgress(0.2);
	        	Leetop.Desktop = Ext.create("Leetop.desktop.Desktop",{
	            	user : "李球"
	            });
	        	Leetop.getLogger = function(){
	        		return Leetop.system.logger.LoggerFactory.getLogger(arguments[0]);
	        	};
	        	Leetop.Console = Ext.create("Leetop.system.Console");
	            window.setTimeout(function(){
	            	Ext.MessageBox.hide();
	            },1000); 
	        });
		};
		Leetop.Bootstrap.loadRequires([{
			type : Leetop.Bootstrap.resTypes.SHORTCUT,
			url : "classes/resources/images/taskbar/favicon.ico"
		  },{
			id : "themecss",
			type : Leetop.Bootstrap.resTypes.CSS,
			url : "lib/ext4/resources/css/"+ theme +".css"
		  },{
			id : "desktopcss",
			type : Leetop.Bootstrap.resTypes.CSS,
			url : "classes/resources/css/desktop.css" 
		  },{
			id : "shortcutcss",
			type : Leetop.Bootstrap.resTypes.CSS,
			url : "classes/resources/css/shortcut.css"
		  },{
			id : "iconcss",
			type : Leetop.Bootstrap.resTypes.CSS,
			url : "classes/resources/css/icon.css"
		  },{
			type : Leetop.Bootstrap.resTypes.JAVASCRIPT,
			url : "lib/ext4/locale/ext-lang-zh_CN.js"
		  }]);
    </script>
	  
	
</head>
<body>
<div id="websocket_button"></div>
       <div>
       </div>
       
</body>


	
</html>
