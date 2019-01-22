package tk.mybatis.springboot.enums;

public enum ExceptionCode {

    RESOURCE_NOT_FOUND(101, "Resource not found");

    private Integer value;

    private String desc;

    private ExceptionCode(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
