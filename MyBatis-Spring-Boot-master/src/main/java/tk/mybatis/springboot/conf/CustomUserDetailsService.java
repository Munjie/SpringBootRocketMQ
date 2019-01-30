package tk.mybatis.springboot.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.model.SysRole;
import tk.mybatis.springboot.model.SysUser;
import tk.mybatis.springboot.model.SysUserRole;
import tk.mybatis.springboot.service.impl.SysRoleServiceImpl;
import tk.mybatis.springboot.service.impl.SysUserRoleServiceImpl;
import tk.mybatis.springboot.service.impl.SysUserServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: 母哥 @Date: 2019-01-29 18:31 @Version 1.0
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserServiceImpl userService;

    @Autowired
    private SysRoleServiceImpl roleService;

    @Autowired
    private SysUserRoleServiceImpl userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUser user = userService.selectByName(username);

        // 判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = roleService.selectById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // 返回UserDetails实现类
        return new User(user.getName(), user.getPassword(), authorities);
    }
}
