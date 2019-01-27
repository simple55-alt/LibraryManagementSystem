package com.winter.service;

import com.github.pagehelper.PageInfo;
import com.winter.model.SysUser;

import java.util.Map;


/**
 * @ClassName UserService
 * @Description TODO
 * @Author ZHENZHEN.ZHANG
 * @Date 2018/10/29 13:35
 * @Version 1.0
 */
public interface UserService {

    Map<String,Object> addUser(SysUser user);

    Map<String,Object> updateOneByKey(SysUser user);

    int deleteAllByKey(String ids);

    Map<String,Object> selectOneByKey(Map<String,Object> param);

    PageInfo selectAllByKey(Map<String,Object> param);


}
