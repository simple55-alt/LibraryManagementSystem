package com.winter.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.github.pagehelper.PageInfo;
import com.winter.conmon.BaseController;
import com.winter.model.SysUser;
import com.winter.service.UserService;
import com.winter.util.SessionUtil;
import com.winter.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author ZHENZHEN.ZHANG
 * @Date 2018/10/29 13:39
 * @Version 1.0
 */

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private static Log log = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/loginPage")
    @ResponseBody
    public Map<String,Object>  loginPage(HttpServletRequest request,HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        String loginCode = request.getParameter("loginCode");
        String loginPassword = request.getParameter("loginPassword");
        String userType = request.getParameter("userType");
        String ip = request.getRemoteHost();
        String Ip=request.getRemoteAddr();

        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isEmpty(loginCode) || StringUtil.isEmpty(loginPassword)){
            map.put("code",0);
            map.put("msg","用户名或密码不能为空");
            return resultMap(map);
        }
        param.put("loginCode",loginCode);
        param.put("loginPassword",StringUtil.getSign(loginPassword));
        param.put("userType",userType);
        map = userService.selectOneByKey(param);
        //设置session
        if(Integer.parseInt(map.get("code").toString()) == 1){
            HttpSession session = request.getSession(true);
            SysUser user = (SysUser) map.get("data");
            SessionUtil.setUserInfo(user);

            String sessionId = session.getId();
            Cookie cookie = new Cookie("JSESSIONID", sessionId);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);

        }
        return resultMap(map);
    }

        /*if ( Integer.valueOf(map.get("code").toString()) == 0) {
            return "redirect:/";
            } else {
            return "redirect:/index";
        }*/
    //登陆成功首页
    @RequestMapping(value = "/index")
    public String  index(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String sessionId = session.getId();
        Cookie[] cookies = request.getCookies();
        List<String> sessionIds = new ArrayList<>();
        if(cookies.length>0){
            for (Cookie cookie : cookies) {
                sessionIds.add(cookie.getValue());
            }
        }
        if(!sessionIds.contains(sessionId)){
            return "/login";
        }else{
            return "/index";
        }
    }

    @RequestMapping(value = "/login")
    public String  login(HttpServletRequest request, HttpSession session) {
        return "/login";
    }

    @RequestMapping(value = "/exit")
    public String  exit(HttpServletRequest request, HttpSession session) {
        return "/login";
    }





    @RequestMapping(value = "/selectUserList",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public  Map<String,Object>  selectUserList(@RequestParam Map<String,Object> param) {
        PageInfo pageInfo = userService.selectAllByKey(param);
        return resultMap(pageInfo);
    }


    @RequestMapping(value = "/getUserOne",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getUserOne(Integer id) {
        Map<String,Object> map = new HashMap<>();
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("id",id);
            map = userService.selectOneByKey(param);
        }catch (Exception e) {
            log.error("查询用户异常:"+e);
            map.put("code",0);
            map.put("msg","查询用户异常");
        }
        return resultMap(map);
    }



    @RequestMapping(value = "/addUser",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addUserInfo(SysUser sysUser, HttpSession session) {
        Map<String,Object> map = new HashMap<>();
        try {
            map = userService.addUser(sysUser);
        }catch (Exception e){
            log.error("新增用户异常:"+e);
            map.put("code",0);
            map.put("msg","新增用户异常");
        }
        return resultMap(map);
    }



    @RequestMapping(value = "/updateUserInfo",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updateUserInfo(SysUser sysUser, HttpSession session) {
        Map<String,Object> map = new HashMap<>();
        try {
            map = userService.updateOneByKey(sysUser);
        }catch (Exception e){
            log.error("修改用户异常");
            map.put("code",0);
            map.put("msg","修改用户异常");
        }
        return resultMap(map);
    }


    @RequestMapping(value = "/delUserInfo",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> delUserInfo(int id,HttpSession session) {

        Map<String,Object> result = new HashMap<>();
//        if(session.getAttribute("id").equals(id)){
//            result.put("msg","违法操作！不能删除自己！");
//            return result;
//        }
        String ids = StringUtil.toString(id);
        if(ids == null){
            result.put("code",0);
            return result;
        }
        try{
            userService.deleteAllByKey(ids);
            result.put("success","true");
            System.out.println("删除Id: "+ids);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","删除异常");
        }
        return result;
    }
}
