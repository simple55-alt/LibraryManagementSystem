package com.winter.service.impl;


import com.github.pagehelper.PageInfo;
import com.winter.dao.UserDao;
import com.winter.model.SysUser;
import com.winter.service.UserService;
import com.winter.util.BaseUtil;
import com.winter.util.PageHelper;
import com.winter.util.SessionUtil;
import com.winter.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author ZHENZHEN.ZHANG
 * @Date 2018/10/29 13:36
 * @Version 1.0
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Override
    public Map<String,Object> addUser(SysUser user) {
        Map<String,Object> resultMap = new HashMap<>();
        boolean flag = checkNameIsNo(user,1);
        if(flag){
            resultMap.put("code",0);
            resultMap.put("msg","用户昵称已存在");
            resultMap.put("data",user);
            return resultMap;
        }
        user.setLoginPassword(MD5(user.getLoginPassword()));
        user.setUserId(BaseUtil.PRIMARYKEY());
        user.setLoginCode(user.getUserNice());
        user.setAddTime(new Date());
        user.setAddBy(SessionUtil.getUserId());
        int add = userDao.insert(user);
        if(add > 0){
            resultMap.put("code",1);
            resultMap.put("msg","新增用户成功");
            resultMap.put("data",user);
        }else{
            resultMap.put("code",0);
            resultMap.put("msg","新增用户异常");
            resultMap.put("data",null);
        }
        return resultMap;
    }

    @Override
    public Map<String,Object> updateOneByKey(SysUser user) {
        Map<String,Object> resultMap = new HashMap<>();
        boolean flag = checkNameIsNo(user,2);
        if(flag){
            resultMap.put("code",0);
            resultMap.put("msg","用户昵称已存在");
            resultMap.put("data",user);
            return resultMap;
        }
        System.out.println("userInfo-------="+SessionUtil.getUserInfo());
        user.setLastTime(new Date());
        user.setLastBy(SessionUtil.getUserId());
        int update = userDao.updateOneByKey(user);
        if(update > 0){
            resultMap.put("code",1);
            resultMap.put("msg","修改用户成功");
            resultMap.put("data",user);
        }else{
            resultMap.put("code",0);
            resultMap.put("msg","修改用户异常");
            resultMap.put("data",null);
        }
        return resultMap;
    }

    @Override
    public int deleteAllByKey(String ids) {
        List<SysUser> list = new ArrayList<>();
        String[] arr = ids.split(",");
        for (String id: arr) {
            SysUser  user= new SysUser();
            user.setId(StringUtil.String2Integer(id));
            user.setUserState(1);
            list.add(user);
        }
        return userDao.updateBatch(list);
    }

    @Override
    public Map<String,Object> selectOneByKey(Map<String,Object> param) {
        Map<String,Object> resultMap = new HashMap<>();
        SysUser user = userDao.selectOneByKey(param);
        if(user != null){
            resultMap.put("code",1);
            resultMap.put("msg","查询用户成功");
            resultMap.put("data",user);
        }else{
            resultMap.put("code",0);
            resultMap.put("msg","查无此人");
            resultMap.put("data",null);
        }
        return resultMap;
    }


    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * */
    @Override
    public PageInfo selectAllByKey(Map<String,Object> param) {
        //将参数传给这个方法实现分页，默认一页十条记录。
        String orderBy = "add_time desc";//排序
        PageHelper.startPage(param,orderBy);

        param.put("userState",0);
        List<SysUser> userDomains = userDao.selectAllByKey(param);
        PageInfo result = new PageInfo(userDomains);
        return result;
    }


    /**
     * @Author 张振镇
     * @Description //检查昵称是否存在
     * @Date 2018/12/14 12:57
     * @Param [] code： 1-新增检查，2-修改检查
     * @return boolean true 存在 ；false 不存在
     **/
    public boolean checkNameIsNo(SysUser user,int code){
        Map<String,Object> map = new HashMap<>();
        if(code == 1){
            //新增检查
            map.put("userNice",user.getUserNice());
            SysUser oldUser = userDao.selectOneByKey(map);
            if(oldUser != null){
                return true;
            }
        }else if(code == 2){
            //修改检查
            map.put("userNice",user.getUserNice());
            int id = user.getId();//传进来的id
            SysUser oldUser = userDao.selectOneByKey(map);
            if(oldUser != null){
                int oldId = oldUser.getId();//查到的id
                if(id != oldId){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @Author 张振镇
     * @Description //md5 加密
     * @Date 2018/12/14 13:12
     * @Param [val]
     * @return void
     **/
    public String MD5(String val){
        return StringUtil.getSign(val);
    }
}
