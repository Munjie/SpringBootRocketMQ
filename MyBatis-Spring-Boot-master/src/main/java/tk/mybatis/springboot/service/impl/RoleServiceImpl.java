package tk.mybatis.springboot.service.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.dao.IRoleDao;
import tk.mybatis.springboot.model.Role;
import tk.mybatis.springboot.service.IRoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 母哥 @Date: 2019-01-22 17:57 @Version 1.0
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public String syncData(String reqData) {
        List<Role> one = roleDao.findOne();
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> list = new ArrayList();
        try {
            for (Role role : one) {

                JSONObject object = new JSONObject();

                object.put("FuncId", role.getFunId());
                object.put("Func", role.getFunc());
                list.add(object);
            }
            jsonObject.put("list", list);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }
}
