package tk.mybatis.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.dao.ISysUserDao;
import tk.mybatis.springboot.model.SysUser;

/**
 * @Author: 母哥 @Date: 2019-01-29 18:22 @Version 1.0
 */
@Service
public class SysUserServiceImpl {

    @Autowired
    private ISysUserDao sysUserDao;

    public SysUser selectById(Integer id) {
        return sysUserDao.selectById(id);
    }

    public SysUser selectByName(String name) {
        return sysUserDao.selectByName(name);
    }
}
