package tk.mybatis.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.springboot.model.SysUserRole;

import java.util.List;

/**
 * @Author: 母哥 @Date: 2019-01-29 18:20 @Version 1.0
 */
@Mapper
public interface ISysUserRoleDao {

    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);
}
