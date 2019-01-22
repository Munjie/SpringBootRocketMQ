package tk.mybatis.springboot.exception;

/**
 * @Author: 母哥 @Date: 2019-01-22 16:56 @Version 1.0
 */
public class MyException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;

    public MyException() {
    }

    public MyException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
