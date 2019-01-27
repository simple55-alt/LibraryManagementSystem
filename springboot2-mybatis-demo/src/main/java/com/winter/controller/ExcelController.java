package com.winter.controller;


import com.winter.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName ExcelController
 * @Description TODO
 * @Author 张振镇
 * @Date 2019/1/27 16:48
 * @Version 1.0
 */

@Controller
@RequestMapping(value = "/importExport")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    public void importExcel(String name, MultipartFile file){
        excelService.importExcel(name,file);

    }


    public void exportExcel(){
        String fileName = "";
        excelService.exportExcel(fileName);

    }
}
