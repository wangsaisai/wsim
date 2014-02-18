package cn.edu.ustc.wsim.test;
import java.util.*;  
  
class R  
{  
    public int count;  
    public R (int count)  
    {  
        this.count = count;  
    }  
    public String toString()  
    {  
        return "R(count " + count + " )";  
    }  
    public boolean equals(Object obj)  
    {  
        if(obj instanceof R)  
        {  
            R r = (R)obj;  
            if( r.count == this.count)  
                return true;  
        }  
        return false;  
    }  
    public int hashCode()  
    {  
        return this.count;  
    }  
}