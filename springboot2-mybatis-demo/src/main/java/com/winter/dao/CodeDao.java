package com.winter.dao;

import com.winter.model.SysCode;

import java.util.List;
import java.util.Map;


public interface CodeDao {

    int insert(SysCode code);

    int updateOneByKey(SysCode code);

    int updateBatch(List<SysCode> list);

    SysCode selectOneByKey(Map<String, Object> param);

    List<SysCode> selectAllByKey(Map<String, Object> param);

}
