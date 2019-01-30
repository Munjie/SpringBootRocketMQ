package tk.mybatis.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.dao.ISysUserRoleDao;
import tk.mybatis.springboot.model.SysUserRole;

import java.util.List;

/**
 * @Author: 母哥
 * @Date: 2019-01-29 18:27
 * @Version 1.0
 */
@Service
public class SysUserRoleServiceImpl {
    @Autowired
    private ISysUserRoleDao userRoleMapper;

    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}

