package com.winter.model.responsevo;

/**
 * @ClassName ResponseVO
 * @Description TODO
 * @Author 张振镇
 * @Date 2018/12/12 16:55
 * @Version 1.0
 */

public class ResponseVO {

    private int code;
    private String msg;
    private Object data;
    private boolean sucess;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", sucess=" + sucess +
                '}';
    }

}
