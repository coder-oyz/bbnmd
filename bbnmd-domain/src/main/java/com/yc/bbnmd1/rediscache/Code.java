package com.yc.bbnmd1.rediscache;



/**
 * @ClassName Code
 * @description: 自定义提示枚举类
 **/
public enum Code {


    /**
     * @Description:  请求状态码
     **/
    SUCCESS(0,"请求成功"),
    ERROR(-1,"请求错误");

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    Code(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}

