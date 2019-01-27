package com.winter.util;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName BaseUtil
 * @Description TODO
 * @Author 张振镇
 * @Date 2018/12/11 13:41
 * @Version 1.0
 */

public class BaseUtil {

    /**
     * @Author 张振镇
     * @Description //一秒只能生成1000个
     * @Date 2018/12/11 14:32
     * @Param []
     * @return java.lang.String
     **/
    public static String PRIMARYKEY(){
        long timeMillis=System.currentTimeMillis();
        String primaryKey = StringUtil.toString(timeMillis);
        return primaryKey;
    }

    /**
     * @Author 张振镇
     * @Description //uuid
     * @Date 2018/12/11 14:43
     * @Param []
     * @return java.lang.String
     **/
    public static String PRIMARYKEYUUID(){
        String PRIMARYKEYUUID= UUID.randomUUID().toString();
        PRIMARYKEYUUID= PRIMARYKEYUUID.replace("-","");
        return PRIMARYKEYUUID;
    }
    



    public static void main(String[] args) {
        PRIMARYKEY();
    }


   

}
