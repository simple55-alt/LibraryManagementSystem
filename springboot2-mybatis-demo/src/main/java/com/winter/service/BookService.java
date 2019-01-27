package com.winter.service;

import com.github.pagehelper.PageInfo;
import com.winter.model.SysBook;

import java.util.Map;

/**
 * @ClassName BookService
 * @Description TODO
 * @Author 张振镇
 * @Date 2018/12/17 17:22
 * @Version 1.0
 */
public interface BookService {

    Map<String,Object> addBook(SysBook book);

    Map<String,Object> updateOneByKey(SysBook book);

    int deleteAllByKey(String ids);

    Map<String,Object> selectOneByKey(Map<String,Object> param);

    PageInfo selectAllByKey(Map<String,Object> param);

}
