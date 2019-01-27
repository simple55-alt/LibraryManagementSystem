package com.winter.dao;

import com.winter.model.SysBook;

import java.util.List;
import java.util.Map;


public interface BookDao {

    int insert(SysBook book);

    int updateOneByKey(SysBook book);

    int updateBatch(List<SysBook> list);

    SysBook selectOneByKey(Map<String, Object> param);

    List<SysBook> selectAllByKey(Map<String, Object> param);

    List<Map<String, Object>> selectAllByKeyTwo(Map<String, Object> param);

}
