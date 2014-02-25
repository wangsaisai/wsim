package cn.edu.ustc.wsim.websocket.user;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.service.UserService;

public class UserinfoUtil

{ 
    @Autowired
    private UserService userService;

    private static UserinfoUtil info;

    public void setUserInfo(UserService userService)
    {
        this.userService = userService;
    }

    @PostConstruct
    public void init()
    {
        info = this;
        info.userService = this.userService;
    }

    public static int addUserLoginCnt(String phonenumber)
    {      
    	if(info.userService == null)
    		System.out.println("nullllllll");
        User user = info.userService.getUserByEmail("1@1.com");
        System.out.println(user.toString());
        return 1;

    }

}