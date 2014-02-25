package cn.edu.ustc.wsim.test;

import java.util.HashSet;
import java.util.Iterator;

public class TestHashSet  
{  
    public static void main(String[] args)  
    {  
        HashSet hs = new HashSet();  
        hs.add(new R(5));  
        hs.add(new R(-3));  
        hs.add(new R(9));  
        hs.add(new R(-2));  
        System.out.println(hs);  
        Iterator it = hs.iterator();  
        R first = (R)it.next();  
        first.count = -3;//直接改变，最好先删除再插入  
        System.out.println(hs);  
        hs.remove(new R(-3));//只查找到了原先的-3  
        System.out.println(hs);  
        System.out.println("has -3 " + hs.contains(new R(-3)));//false  
        System.out.println("has -5 " + hs.contains(new R(-5)));//false  
    }  
}  