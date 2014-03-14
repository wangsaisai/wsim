package cn.edu.ustc.wsim.action;
import java.util.ArrayList;
import java.util.List;

import cn.edu.ustc.wsim.bean.Menu;

import com.opensymphony.xwork2.ActionSupport;
  
public class TreeAction extends ActionSupport {  
    private List menus; //数据  
      
    private String root;    //接收节点信息  
      
    public String tree(){  
System.out.println("root:"+root);  
  
        //当root为空，表示是根节点  
        if("".equals(root)){  
            menus = new ArrayList();  
  
            //目录  
            Menu jianjie = new Menu();  
            jianjie.setText("简介");  
            jianjie.setIds("jianjie");  
            jianjie.setCls("file");  
            jianjie.setLeaf(true);  
              
            Menu diyizhang = new Menu();  
            diyizhang.setText("第一章");  
            diyizhang.setIds("diyizhang");  
            diyizhang.setCls("folder");  
            diyizhang.setLeaf(false);  
              
            Menu dierzhang = new Menu();  
            dierzhang.setText("第二章");  
            dierzhang.setIds("dierzhang");  
            dierzhang.setCls("folder");  
            dierzhang.setLeaf(false);  
              
            Menu zongjie = new Menu();  
            zongjie.setIds("zongjie");  
            zongjie.setText("总结");  
            zongjie.setCls("file");  
            zongjie.setLeaf(true);  
              
            //根节点信息  
            menus.add(jianjie);  
            menus.add(diyizhang);  
            menus.add(dierzhang);  
            menus.add(zongjie);  
        }else if("diyizhang".equals(root)){  
            menus = new ArrayList();  
              
            Menu diyijie = new Menu();  
            diyijie.setText("第一章-第一节");  
            diyijie.setCls("file");  
            diyijie.setIds("diyijie");  
            diyijie.setLeaf(true);  
              
            Menu dierjie = new Menu();  
            dierjie.setText("第一章-第二节");  
            dierjie.setIds("dierjie");  
            dierjie.setLeaf(true);  
              
            menus.add(diyijie);  
            menus.add(dierjie);  
        }else if("dierzhang".equals(root)){  
            menus = new ArrayList();  
              
            Menu diyijie = new Menu();  
            diyijie.setText("第二章-第一节");  
            diyijie.setCls("file");  
            diyijie.setIds("diyijie");  
            diyijie.setLeaf(true);  
              
            Menu dierjie = new Menu();  
            dierjie.setText("第二章-第二节");  
            dierjie.setIds("dierjie");  
            dierjie.setLeaf(true);  
              
            menus.add(diyijie);  
            menus.add(dierjie);  
        }  
          
System.out.println(menus);  
  
        return SUCCESS;  
    }  
      
    public List getMenus() {  
        return menus;  
    }  
  
    public void setMenus(List menus) {  
        this.menus = menus;  
    }  
  
    public String getRoot() {  
        return root;  
    }  
  
    public void setRoot(String root) {  
        this.root = root;  
    }  
      
      
}  