package tk.mybatis.springboot.model;

import java.io.Serializable;

/**
 * @Author: 母哥 @Date: 2019-01-29 18:13 @Version 1.0
 */
public class SysUserRole implements Serializable {

    static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer roleId;

    public SysUserRole() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    // 省略getter/setter
}
