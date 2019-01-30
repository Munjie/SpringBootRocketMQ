package tk.mybatis.springboot.model;

import java.io.Serializable;

/**
 * @Author: 母哥 @Date: 2019-01-29 18:13 @Version 1.0
 */
public class SysRole implements Serializable {

    static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 省略getter/setter
}
