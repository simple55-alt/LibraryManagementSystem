package com.winter.service;

import com.github.pagehelper.PageInfo;
import com.winter.model.SysCode;

import java.util.Map;

/**
 * @ClassName CodeService
 * @Description TODO
 * @Author 张振镇
 * @Date 2018/12/17 17:22
 * @Version 1.0
 */
public interface CodeService {

    Map<String,Object> addCode(SysCode code);

    Map<String,Object> updateOneByKey(SysCode code);

    int deleteAllByKey(String ids);

    Map<String,Object> selectOneByKey(Map<String, Object> param);

    PageInfo selectAllByKey(Map<String, Object> param);



}
