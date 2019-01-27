package com.winter.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.github.pagehelper.PageInfo;
import com.winter.conmon.BaseController;
import com.winter.model.SysCode;
import com.winter.service.CodeService;
import com.winter.service.UserService;
import com.winter.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName codeController
 * @Description TODO
 * @Author 张振镇
 * @Date 2018/12/17 17:08
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/code")
public class CodeController extends BaseController {

    private static Log log = LogFactory.getLog(CodeController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CodeService codeService;

    @RequestMapping(value = "/selectCodeList",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public List<SysCode> selectCodeList(Map<String,Object> param,String codeKey,String codeParentId) {
        param.put("codeKey",codeKey);
        param.put("codeParentId",codeParentId);
        PageInfo pageInfo = codeService.selectAllByKey(param);
        List<SysCode> sysCodes = pageInfo.getList();
        return sysCodes;
    }


    @RequestMapping(value = "/getCodeOne",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getCodeOne(Integer id) {
        Map<String,Object> map = new HashMap<>();
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("id",id);
            map = codeService.selectOneByKey(param);
        }catch (Exception e) {
            log.error("查询书籍异常:"+e);
            map.put("code",0);
            map.put("msg","查询书籍异常");
        }
        return resultMap(map);
    }



    @RequestMapping(value = "/addcode",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addcodeInfo(SysCode sysCode) {
        Map<String,Object> map = new HashMap<>();
        try {
            map = codeService.addCode(sysCode);
        }catch (Exception e){
            log.error("新增用户异常:"+e);
            map.put("code",0);
            map.put("msg","新增用户异常");
        }
        return resultMap(map);
    }



    @RequestMapping(value = "/updateCodeInfo",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updateCodeInfo(SysCode sysCode) {
        Map<String,Object> map = new HashMap<>();
        try {
            map =codeService.updateOneByKey(sysCode);
        }catch (Exception e){
            log.error("修改书籍异常");
            map.put("code",0);
            map.put("msg","修改书籍异常");
        }
        return resultMap(map);
    }


    @RequestMapping(value = "/delCodeInfo",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> delCodeInfo(int id) {

        Map<String,Object> result = new HashMap<>();
//        if(session.getAttribute("id").equals(id)){
//            result.put("msg","违法操作！不能删除自己！");
//            return result;
//        }
        String ids = StringUtil.toString(id);
        if(ids == null){
            result.put("code",0);
            return result;
        }
        try{
            codeService.deleteAllByKey(ids);
            result.put("success","true");
            System.out.println("删除Id: "+ids);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","删除异常");
        }
        return result;
    }





}
