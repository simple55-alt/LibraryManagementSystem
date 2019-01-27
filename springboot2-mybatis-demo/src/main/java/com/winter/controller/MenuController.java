package com.winter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName MenuController
 * @Description TODO
 * @Author 张振镇
 * @Date 2018/12/10 15:09
 * @Version 1.0
 */
@Controller
public class MenuController {


    //用户信息管理
    @RequestMapping(value = "/user_infomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String userinfo(HttpSession session) {
        return "/view/common/user_information";
    }

    //图书管理
    @RequestMapping(value = "/book_infomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String bookinfo(HttpSession session) {
        return "/view/common/book_information";
    }


}
