package tk.mybatis.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.springboot.model.SysUser;

/**
 * @Author: 母哥 @Date: 2019-01-29 18:16 @Version 1.0
 */
@Mapper
public interface ISysUserDao {

    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SysUser selectById(Integer id);

    @Select("SELECT * FROM sys_user WHERE name = #{name}")
    SysUser selectByName(String name);
}
