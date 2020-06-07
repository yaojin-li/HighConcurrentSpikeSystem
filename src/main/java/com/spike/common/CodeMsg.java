package com.spike.common;

/**
 * @Description: --------------------------------------
 * @ClassName: CodeMsg.java
 * @Date: 2020/6/7 10:18
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class CodeMsg {
    private int code;
    private String msg;

    /**
     * 通用
     * */
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");

    //登录模块 5002XX

    //商品模块 5003XX

    //订单模块 5004XX

    //秒杀模块 5005XX



    private CodeMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

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
}
