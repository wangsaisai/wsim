package cn.edu.ustc.wsim.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.ustc.wsim.bean.Admin;
import cn.edu.ustc.wsim.service.AdminService;
import cn.edu.ustc.wsim.service.impl.AdminServiceImpl;
  
public class Main {  
    public static void main(String args[]){  
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml") ;
        
        
//        GlobalFinal gf = (GlobalFinal)context.getBean("globalFinal") ;  
//        System.out.println(gf.getServerIP()) ;  
        
        AdminService as = new AdminServiceImpl();
        Admin a = (Admin) as.get(1);
        System.out.println("    " + a.getEmail());
    }  
}  