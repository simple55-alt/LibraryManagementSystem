package com.winter.service.impl;


import com.github.pagehelper.PageInfo;
import com.winter.dao.BookDao;
import com.winter.dao.UserDao;
import com.winter.model.SysBook;
import com.winter.model.SysUser;
import com.winter.service.BookService;
import com.winter.service.UserService;
import com.winter.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName BookServiceImpl
 * @Description TODO
 * @Author ZHENZHEN.ZHANG
 * @Date 2018/10/29 13:36
 * @Version 1.0
 */

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public Map<String,Object> addBook(SysBook book) {
        Map<String,Object> resultMap = new HashMap<>();
        book.setBookCode("book_"+BaseUtil.PRIMARYKEY());
        book.setAddBy(SessionUtil.getUserId());
        book.setAddTime(new Date());
        int add = bookDao.insert(book);
        if(add > 0){
            resultMap.put("code",1);
            resultMap.put("msg","新增图书信息成功");
            resultMap.put("data",book);
        }else{
            resultMap.put("code",0);
            resultMap.put("msg","新增图书信息异常");
            resultMap.put("data",null);
        }
        return resultMap;
    }

    @Override
    public Map<String,Object> updateOneByKey(SysBook book) {
        Map<String,Object> resultMap = new HashMap<>();

        book.setLastTime(new Date());
        book.setLastBy(SessionUtil.getUserId());
        int update = bookDao.updateOneByKey(book);
        if(update > 0){
            resultMap.put("code",1);
            resultMap.put("msg","修改图书信息成功");
            resultMap.put("data",book);
        }else{
            resultMap.put("code",0);
            resultMap.put("msg","修改图书信息异常");
            resultMap.put("data",null);
        }
        return resultMap;
    }

    @Override
    public int deleteAllByKey(String ids) {
        List<SysBook> list = new ArrayList<>();
        String[] arr = ids.split(",");
        for (String id: arr) {
            SysBook  book= new SysBook();
            book.setId(StringUtil.String2Integer(id));
            book.setDeleteFlag(1);
            book.setBookState(1);
            list.add(book);
        }
        return bookDao.updateBatch(list);
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
        String orderBy = "add_time desc";//排序
        PageHelper.startPage(param,orderBy);
        param.put("deleteFlag",0);//未删除
        List<SysBook> list = bookDao.selectAllByKey(param);
//        List<Map<String,Object>> list = bookDao.selectAllByKeyTwo(param);
//        list = MapUtils.formatHumpNameForList(list);

        PageInfo result = new PageInfo(list);
        return result;
    }

}
