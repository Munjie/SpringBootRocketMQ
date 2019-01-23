package tk.mybatis.springboot.service.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.dao.IRoleDao;
import tk.mybatis.springboot.model.Role;
import tk.mybatis.springboot.service.IRoleService;
import tk.mybatis.springboot.util.AESUtil;
import tk.mybatis.springboot.util.Common;

import java.util.List;

/**
 * @Author: 母哥 @Date: 2019-01-22 17:57 @Version 1.0
 */
@Service
public class RoleServiceImpl extends Common implements IRoleService {

    protected static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private IRoleDao roleDao;

    @Override
    public String syncData(String reqData) {
        String encrypt = AESUtil.encrypt(reqData, "123456");
        logger.info("encrypt content" + encrypt);
        String decrypt = AESUtil.decrypt(encrypt, "123456");
        logger.info("decrypt content" + decrypt);
        List<Role> one = roleDao.findOne();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            for (Role role : one) {

                JSONObject object = new JSONObject();

                object.put("funcId", role.getFunId());
                object.put("func", role.getFunc());
                jsonArray.put(object);
            }
            jsonObject.put("code", Common.SUCCESS_CODE);
            jsonObject.put("message", Common.SUCCESS_MESSAGE);
            jsonObject.put("list", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        logger.info("返回json为：" + jsonObject.toString());
        return jsonObject.toString();
  }
}
