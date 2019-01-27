package com.winter.conmon;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author 张振镇
 * @Date 2018/12/11 11:24
 * @Version 1.0
 */

public class BaseController {

    /**
     * @Author 张振镇
     * @Description //TODO
     * @Date 2018/12/11 11:28
     * @Param [code, msg, data]
     * code : 0-失败，1-成功
     * msg : 消息提醒
     * data : 返回对象
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public static Map<String,Object> resultMap(Map<String,Object> map){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg",map.get("msg"));
        resultMap.put("code",map.get("code"));
        resultMap.put("data",map.get("data"));
        if(Integer.valueOf(map.get("code").toString()) == 0){
            resultMap.put("success",false);
        }else{
            resultMap.put("success",true);
        }
        return resultMap;
    }


    /**
     * @Author 张振镇
     * @Description //分页查询返回
     * @Date 2018/12/11 13:38
     * @Param [pageInfo]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public static Map<String,Object> resultMap(PageInfo pageInfo){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("total",pageInfo.getTotal());
        resultMap.put("rows",pageInfo.getList());
        return resultMap;
    }

}
