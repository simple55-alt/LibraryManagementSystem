package com.winter.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName SysBook
 * @Description TODO
 * @Author 张振镇
 * @Date 2018/12/17 17:17
 * @Version 1.0
 */

public class SysBook {

    private int id;
    private String bookCode;
    private String bookName;
    private String bookType;
    private String bookAuthor;
    private String bookAddr;
    private BigDecimal bookAmount;
    private int bookDisFlag;
    private int bookState;
    private int deleteFlag;
    private Date addTime;
    private String  addBy;
    private Date    lastTime;
    private String  lastBy;

    private SysCode sysCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAddr() {
        return bookAddr;
    }

    public void setBookAddr(String bookAddr) {
        this.bookAddr = bookAddr;
    }

    public BigDecimal getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(BigDecimal bookAmount) {
        this.bookAmount = bookAmount;
    }

    public int getBookDisFlag() {
        return bookDisFlag;
    }

    public void setBookDisFlag(int bookDisFlag) {
        this.bookDisFlag = bookDisFlag;
    }

    public int getBookState() {
        return bookState;
    }

    public void setBookState(int bookState) {
        this.bookState = bookState;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public SysCode getSysCode() {
        return sysCode;
    }

    public void setSysCode(SysCode sysCode) {
        this.sysCode = sysCode;
    }
}
