package com.winter.service.impl;


import com.github.pagehelper.PageInfo;
import com.winter.dao.CodeDao;
import com.winter.dao.UserDao;
import com.winter.model.SysCode;
import com.winter.model.SysUser;
import com.winter.service.CodeService;
import com.winter.util.PageHelper;
import com.winter.util.SessionUtil;
import com.winter.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName codeServiceImpl
 * @Description TODO
 * @Author ZHENZHEN.ZHANG
 * @Date 2018/10/29 13:36
 * @Version 1.0
 */

@Service
@Transactional
public class CodeServiceImpl implements CodeService {

    @Autowired
    private HttpSession session;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CodeDao codeDao;

    @Override
    public Map<String,Object> addCode(SysCode code) {
        Map<String,Object> resultMap = new HashMap<>();
        code.setAddBy(SessionUtil.getUserId());
        code.setAddTime(new Date());
        int add = codeDao.insert(code);
        if(add > 0){
            resultMap.put("code",1);
            resultMap.put("msg","新增图书信息成功");
            resultMap.put("data",code);
        }else{
            resultMap.put("code",0);
            resultMap.put("msg","新增图书信息异常");
            resultMap.put("data",null);
        }
        return resultMap;
    }

    @Override
    public Map<String,Object> updateOneByKey(SysCode code) {
        Map<String,Object> resultMap = new HashMap<>();

        code.setLastTime(new Date());
        code.setLastBy(SessionUtil.getUserId());
        int update = codeDao.updateOneByKey(code);
        if(update > 0){
            resultMap.put("code",1);
            resultMap.put("msg","修改图书信息成功");
            resultMap.put("data",code);
        }else{
            resultMap.put("code",0);
            resultMap.put("msg","修改图书信息异常");
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
            resultMap.put("msg","查询书籍成功");
            resultMap.put("data",user);
        }else{
            resultMap.put("code",0);
            resultMap.put("msg","查无此书");
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
//        String orderBy = "add_time desc";//排序
//        PageHelper.startPage(param,orderBy);
        param.put("codeState",0);//未删除
        List<SysCode> codes = codeDao.selectAllByKey(param);
        PageInfo result = new PageInfo(codes);
        return result;
    }

}
