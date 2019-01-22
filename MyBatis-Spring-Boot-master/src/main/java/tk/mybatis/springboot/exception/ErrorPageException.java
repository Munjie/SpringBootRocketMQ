package tk.mybatis.springboot.exception;

public class ErrorPageException extends RuntimeException {


    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;

    public ErrorPageException(String message, Throwable cause, String code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public ErrorPageException(String code, String msg) {
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
