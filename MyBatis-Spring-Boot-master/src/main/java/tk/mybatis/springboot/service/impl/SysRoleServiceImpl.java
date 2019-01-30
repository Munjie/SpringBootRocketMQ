package tk.mybatis.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.dao.ISysRoleDao;
import tk.mybatis.springboot.model.SysRole;

/**
 * @Author: 母哥 @Date: 2019-01-29 18:25 @Version 1.0
 */
@Service
public class SysRoleServiceImpl {

    @Autowired
    private ISysRoleDao sysRoleDao;

    public SysRole selectById(Integer id) {
        return sysRoleDao.selectById(id);
    }
}
