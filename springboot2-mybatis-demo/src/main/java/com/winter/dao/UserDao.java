package com.winter.dao;

import com.winter.model.SysUser;

import java.util.List;
import java.util.Map;


public interface UserDao {

    int insert(SysUser sysUser);

    int updateOneByKey(SysUser sysUser);

    int updateBatch(List<SysUser> list);

    SysUser selectOneByKey(Map<String,Object> param);

    List<SysUser> selectAllByKey(Map<String,Object> param);

}
