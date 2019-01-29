package tk.mybatis.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.springboot.dao.IRoleDao;
import tk.mybatis.springboot.model.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 母哥 @Date: 2019-01-28 18:22 @Version 1.0
 */
@Controller
public class IndexController {

    protected static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IRoleDao roleDao;

    @RequestMapping("home")
    public String goHome(Model map) throws Exception {
        logger.info("welcome to home");
        List<Role> one = roleDao.findOne();
        List userinfo = new ArrayList();
        for (Role role : one) {

            userinfo.add(role.getFunc());
            userinfo.add(role.getFunId());
        }

        map.addAttribute("userinfo", userinfo);

        return "index";
    }
}
