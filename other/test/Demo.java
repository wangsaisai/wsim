package cn.edu.ustc.wsim.util;

import net.sf.json.JSONObject;


public class Demo {  
    public static void main(String[] args) throws Exception {  
        String str = "{\"a\":\"b\", \"c\":\"d\"}";  
        JSONObject a = JSONObject.fromObject(str);
        System.out.println(a); // {"c":"d","a":"b"}  
        System.out.println(a.get("c")); // d  
    }  
}  