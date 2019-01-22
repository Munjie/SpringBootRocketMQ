package tk.mybatis.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.springboot.model.Role;

/**
 * @Author: 母哥 @Date: 2019-01-22 16:56 @Version 1.0
 */
@Component
@Mapper
public interface RoleMapper {

    @Select({"select * from func"})
    Role findOne();
}
