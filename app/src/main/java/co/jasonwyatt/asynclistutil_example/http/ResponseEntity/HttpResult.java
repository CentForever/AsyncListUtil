package co.jasonwyatt.asynclistutil_example.http.ResponseEntity;

import java.io.Serializable;

public class HttpResult<T> implements Serializable {
    /*public String code;
    public String msg;
    public boolean hasmore;
    public T data;
    public static String SUCCESS = "000";
    public static String SIGN_OUT = "101";//token验证失败
    public static String SHOWTOAST = "102";//显示Toast

    public boolean isSuccess() {
        return SUCCESS.equals(code);
    }

    public boolean isTokenInvalid() {
        return SIGN_OUT.equals(code);
    }

    public boolean isShowToast() {
        return SHOWTOAST.equals(code);
    }

    public boolean hasMore() {
        return hasmore;
    }*/

    private int code;
    private String msg;
    T ret;

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

    public T getRet() {
        return ret;
    }

    public void setRet(T ret) {
        this.ret = ret;
    }
}


