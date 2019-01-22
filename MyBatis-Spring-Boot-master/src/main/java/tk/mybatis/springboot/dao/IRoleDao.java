package tk.mybatis.springboot.dao;

import org.springframework.stereotype.Component;
import tk.mybatis.springboot.model.Role;

import java.util.List;

/**
 * @Author: 母哥 @Date: 2019-01-22 16:56 @Version 1.0
 */
@Component
public interface IRoleDao {

    List<Role> findOne();
}
