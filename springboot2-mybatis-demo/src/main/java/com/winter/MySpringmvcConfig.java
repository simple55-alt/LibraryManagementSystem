package com.winter;

import com.winter.util.Interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName MySpringmvcConfig
 * @Description TODO
 * @Author 张振镇
 * @Date 2019/1/11 15:23
 * @Version 1.0
 */

@Configuration
public class MySpringmvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/**")
            .excludePathPatterns("/user/login") //登录页
            .excludePathPatterns("/user/sendEmail") //发送邮箱
            .excludePathPatterns("/user/register") //用户注册
            .excludePathPatterns("/user/loginPage") //用户登录
            .excludePathPatterns("/static/css/**", "/static/plugins/**");//静态资源

    }
}
