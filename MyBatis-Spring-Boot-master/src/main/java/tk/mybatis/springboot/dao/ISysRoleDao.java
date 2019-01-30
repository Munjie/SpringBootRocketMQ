package tk.mybatis.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.springboot.model.SysRole;

/**
 * @Author: 母哥 @Date: 2019-01-29 18:17 @Version 1.0
 */
@Mapper
public interface ISysRoleDao {
    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    SysRole selectById(Integer id);
}
