package com.winter.util;

import com.github.pagehelper.Page;

import java.util.Map;

/**
 * @ClassName PageHelper
 * @Description TODO
 * @Author 张振镇
 * @Date 2018/12/13 11:55
 * @Version 1.0
 */

public class PageHelper extends com.github.pagehelper.PageHelper {


    /**
     * 开始分页
     *
     * @param param
     */
    public static <E> Page<E> startPage(Map<String,Object> param) {
        String pages= StringUtil.toString(param.get("page"));
        String pageSizes= StringUtil.toString(param.get("rows"));
        int pageNum = 1;//页码
        int pageSize = 10;//每页显示数量
        if(StringUtil.isNotEmpty(pages)){
            pageNum = StringUtil.String2Integer(pages);
        }
        if(StringUtil.isNotEmpty(pageSizes)){
            pageSize = StringUtil.String2Integer(pageSizes);
        }
        return startPage(pageNum, pageSize, DEFAULT_COUNT);
    }


    /**
     * 开始分页
     * @param param
     * @param orderBy  排序
     */
    public static <E> Page<E> startPage(Map<String,Object> param, String orderBy) {
        Page<E> page = startPage(param);
        page.setOrderBy(orderBy);
        return page;
    }

}
