package com.winter.util.Interceptor;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.winter.model.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName UserLoginInterceptor
 * @Description 利用spring框架提供的HandlerInterceptorAdapter，实现自定义拦截器
 * @Author 张振镇
 * @Date 2019/1/11 15:05
 * @Version 1.0
 */

public class UserLoginInterceptor implements HandlerInterceptor {


    // 在业务处理器处理请求之前被调用
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        System.out.println("url="+request.getRequestURI());

        //验证session是否存在
        Object obj = request.getSession().getAttribute("userInfo");
        if(obj == null){
            response.getWriter().print("SESSION_OUT");
            response.sendRedirect("/user/login");
            return false;
        }
        return true;
    }


    // 在业务处理器处理请求完成之后，生成视图之前执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        System.out.println("postHandle...");
        if(modelAndView != null){
            Map<String, String> map = new HashMap<String, String>();
            modelAndView.addAllObjects(map);
        }
    }
    // 在DispatcherServlet完全处理完请求之后被调用，可用于清理资源
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        System.out.println("afterCompletion...");
    }

}
