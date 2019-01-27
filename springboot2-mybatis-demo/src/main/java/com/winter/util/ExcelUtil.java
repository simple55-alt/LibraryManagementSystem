package com.winter.util;

/**
 * @ClassName ExcelUtil
 * @Description TODO
 * @Author 张振镇
 * @Date 2019/1/27 19:47
 * @Version 1.0
 */

public class ExcelUtil {

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
