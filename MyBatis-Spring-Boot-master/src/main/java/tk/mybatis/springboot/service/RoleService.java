package tk.mybatis.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.dao.IRoleDao;
import tk.mybatis.springboot.model.Role;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private IRoleDao roleDao;

    public List<Role> findOne() {

        return roleDao.findOne();
    }

    ;
}
