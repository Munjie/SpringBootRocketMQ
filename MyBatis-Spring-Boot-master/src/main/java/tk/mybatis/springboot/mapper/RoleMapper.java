package tk.mybatis.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.springboot.model.Country;
import tk.mybatis.springboot.model.Role;
@Component
@Mapper
public interface RoleMapper {

    @Select({"select * from func"})
    Role findOne();
}
