package com.winter.util;

import java.util.*;
/**
 * @ClassName MapUtils
 * @Description TODO
 * @Author 张振镇
 * @Date 2019/1/24 15:04

 * Map工具类<br>
 *
 * @author Wesley<br>
 *
 */
public class MapUtils {

    /**
     * 将Map的Keys转译成下划线格式的<br>
     * (例:branchNo -> branch_no)<br>
     *
     * @param map
     *            待转换Map
     * @return
     */
    public static <V> Map<String, V> formatUnderline(Map<String, V> map) {
        Map<String, V> newMap = new HashMap<String, V>();
        for (String key : map.keySet()) {
            newMap.put(toUnderlineString(key), map.get(key));
        }
        return newMap;
    }

    /**
     * 将属性样式字符串转成下划线样式字符串<br>
     * (例:branchNo -> branch_no)<br>
     *
     * @param inputString
     * @return
     */
    public static String toUnderlineString(String inputString) {
        if (inputString == null)
            return null;
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            boolean nextUpperCase = true;

            if (i < (inputString.length() - 1)) {
                nextUpperCase = Character.isUpperCase(inputString.charAt(i + 1));
            }

            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0)
                        sb.append("_");
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }


    /**
     * 将Map中的key由下划线转换为驼峰
     *
     * @param map
     * @return
     */
    public static Map<String, Object> formatHumpName(Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<String, Object>();
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            String newKey = toFormatCol(key);
            newMap.put(newKey, entry.getValue());
        }
        return newMap;
    }

    public static String toFormatCol(String colName) {
        StringBuilder sb = new StringBuilder();
        String[] str = colName.toLowerCase().split("_");
        int i = 0;
        for (String s : str) {
            if (s.length() == 1) {
                s = s.toUpperCase();
            }
            i++;
            if (i == 1) {
                sb.append(s);
                continue;
            }
            if (s.length() > 0) {
                sb.append(s.substring(0, 1).toUpperCase());
                sb.append(s.substring(1));
            }
        }
        return sb.toString();
    }

    /**
     * 将List中map的key值命名方式格式化为驼峰
     *
     * @param
     * @return
     */
    public static List<Map<String, Object>> formatHumpNameForList(List<Map<String, Object>> list) {
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> o : list) {
            newList.add(formatHumpName(o));
        }
        return newList;
    }


    /**
     * 将List中map的key值命名方式格式化为下划线
     *
     * @param
     * @return
     */
    public static List<Map<String, Object>> formatUnderlineForList(List<Map<String, Object>> list) {
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> o : list) {
            newList.add(formatUnderline(o));
        }
        return newList;
    }

    /**
     * @Author 张振镇
     * @Description mapList分组
     * @Date 2019/1/24 17:09
     * @Param [mapList, column]
     * @return java.util.Map<java.lang.String,java.util.List<java.util.Map<java.lang.String,java.lang.Object>>>
     **/

    public static Map<String,List<Map<String,Object>>> groupByMapForList(List<Map<String, Object>> mapList,String column){

        Map<String, List<Map<String, Object>>> resultMap= new HashMap<>();
        for(int i=0;i<mapList.size();i++){
            Map<String, Object> dataItem = mapList.get(i);
            if(resultMap.containsKey(dataItem.get(column))){
                resultMap.get(dataItem.get(column)).add(dataItem);
            }else{
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                list.add(dataItem);
                resultMap.put((String) dataItem.get(column),list);
            }
        }
        return resultMap;
    }



    public static void main(String[] args) {
        Map<String,Object> map1= new HashMap<>();
        map1.put("book_name","china");
        map1 =  formatHumpName(map1);

        Map<String,Object> map2= new HashMap<>();
        map2.put("bookName","china");
        map2 = formatUnderline(map2);

        System.out.println(map1);
        System.out.println(map2);
    }



}
