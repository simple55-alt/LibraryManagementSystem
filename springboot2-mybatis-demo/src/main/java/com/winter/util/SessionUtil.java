package com.winter.util;

import com.winter.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SessionUtil
 * @Description TODO
 * @Author 张振镇
 * @Date 2018/12/17 11:53
 * @Version 1.0
 */

public class SessionUtil {

    public static HttpSession getSesstion(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session= request.getSession();
        return session;
    }

    public static String getUserId(){
        HttpSession session =  getSesstion();
        SysUser  user=(SysUser) session.getAttribute("userInfo");
        return user.getUserId();
    }


    public static SysUser getUserInfo(){
        HttpSession session =  getSesstion();
        SysUser  user=(SysUser) session.getAttribute("userInfo");
        return user;
    }

    public static void setUserInfo(SysUser user){
        if(user == null){
            user = setGetUser();
        }
        System.out.println("userNice======"+user.getUserNice());
        HttpSession session =  getSesstion();
        session.setAttribute("userInfo",user);
        session.setMaxInactiveInterval(60*60);//以秒为单位
    }


    public static SysUser setGetUser(){
        SysUser user = new SysUser();
        user.setUserId("0000000000000");
        user.setLoginCode("李小龙");
        user.setLoginPassword("123456");
        user.setUserNice("龙的传人");
        return user;
    }


}
