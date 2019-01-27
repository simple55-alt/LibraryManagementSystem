package com.winter.model;

import java.util.Date;

/**
 * @ClassName SysCode
 * @Description TODO
 * @Author 张振镇
 * @Date 2019/1/23 15:04
 * @Version 1.0
 */

public class SysCode {


    private int id;
    private String codeId;
    private String codeText;
    private String codeParentId;
    private String codeKey;
    private int codeState;
    private Date addTime;
    private String  addBy;
    private Date    lastTime;
    private String  lastBy;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }

    public String getCodeParentId() {
        return codeParentId;
    }

    public void setCodeParentId(String codeParentId) {
        this.codeParentId = codeParentId;
    }

    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey;
    }

    public int getCodeState() {
        return codeState;
    }

    public void setCodeState(int codeState) {
        this.codeState = codeState;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastBy() {
        return lastBy;
    }

    public void setLastBy(String lastBy) {
        this.lastBy = lastBy;
    }
}
