package com.winter.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.github.pagehelper.PageInfo;
import com.winter.conmon.BaseController;
import com.winter.model.SysBook;
import com.winter.service.BookService;
import com.winter.service.UserService;
import com.winter.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BookController
 * @Description TODO
 * @Author 张振镇
 * @Date 2018/12/17 17:08
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/book")
public class BookController extends BaseController {

    private static Log log = LogFactory.getLog(BookController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/selectBookList",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> selectBookList(@RequestParam Map<String,Object> param) {
        PageInfo pageInfo = bookService.selectAllByKey(param);
        return resultMap(pageInfo);
    }


    @RequestMapping(value = "/getBookOne",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getBookOne(Integer id) {
        Map<String,Object> map = new HashMap<>();
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("id",id);
            map = bookService.selectOneByKey(param);
        }catch (Exception e) {
            log.error("查询书籍异常:"+e);
            map.put("code",0);
            map.put("msg","查询书籍异常");
        }
        return resultMap(map);
    }



    @RequestMapping(value = "/addBook",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addBookInfo(SysBook sysBook) {
        Map<String,Object> map = new HashMap<>();
        try {
            map = bookService.addBook(sysBook);
        }catch (Exception e){
            log.error("新增用户异常:"+e);
            map.put("code",0);
            map.put("msg","新增用户异常");
        }
        return resultMap(map);
    }



    @RequestMapping(value = "/updateBookInfo",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updateBookInfo(SysBook sysBook) {
        Map<String,Object> map = new HashMap<>();
        try {
            map =bookService.updateOneByKey(sysBook);
        }catch (Exception e){
            log.error("修改书籍异常");
            map.put("code",0);
            map.put("msg","修改书籍异常");
        }
        return resultMap(map);
    }


    @RequestMapping(value = "/delBookInfo",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> delBookInfo(int id) {

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
            bookService.deleteAllByKey(ids);
            result.put("success","true");
            System.out.println("删除Id: "+ids);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","删除异常");
        }
        return result;
    }





}
