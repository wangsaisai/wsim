$(function(){ 
		  

	$("#statusStr").change(function(){
		
		$.ajax({
			type:"post",
			url:"changeStatusTemp",//需要用来处理ajax请求的action
			data:{//设置数据源
				statusStr:$("#statusStr").val()
				//最后一项这里不要加"," 其它参数需要加  不然会报错，而且根本不会提示错误地方
			},
			dataType:"json",//设置需要返回的数据类型
			success:function(data){
				var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
				//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
				$("#show").text(""+d.result+"");
				
			},
			error:function(){
				alert("系统异常，请稍后重试！");
			}//这里不要加","
		});
		
	})
	  
})
