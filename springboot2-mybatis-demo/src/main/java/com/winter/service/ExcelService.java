package com.winter.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName ExcelService
 * @Description TODO
 * @Author 张振镇
 * @Date 2019/1/27 17:23
 * @Version 1.0
 */
public interface ExcelService {

    //导入
    public void importExcel(String fileName, MultipartFile file);

    //导出
    public void exportExcel(String fileName);
}
