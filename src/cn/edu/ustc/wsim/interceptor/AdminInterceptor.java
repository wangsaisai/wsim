package cn.edu.ustc.wsim.interceptor;

import java.util.Map;

import cn.edu.ustc.wsim.bean.Admin;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context =invocation.getInvocationContext();
		//获得session登录
		Map session = context.getContext().getSession();
		Admin amdin = (Admin) session.get("admin");
		//判断管理员是否
		if(amdin == null) {
			//返回用户登陆页面
			return "adminLogin";
		} else {
			//进行下一步操作，没有拦截
			return invocation.invoke();
		}
	}

}
