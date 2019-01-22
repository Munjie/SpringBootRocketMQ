package tk.mybatis.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.springboot.exception.ErrorPageException;
import tk.mybatis.springboot.mq.mqimpl.RocketMQProducerSend;
import tk.mybatis.springboot.service.IRoleService;

/**
 * @Author: 母哥 @Date: 2019-01-01 13:56 @Version 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    protected static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

    @Autowired
    private RocketMQProducerSend rocketMQProducerSend;

    @RequestMapping(value = "/find", produces = "application/json;charset=utf-8")
    public String find(@RequestBody String reqData) {

        logger.info(reqData);
        rocketMQProducerSend.sendMsgToMQServer(reqData);
        String s = roleService.syncData(reqData);
        //  mqConsumer.consumerCost();
        String temp = null;
        return s;
    }

    @RequestMapping("/log")
    public String Test(@RequestParam(value = "role") Integer role) throws Exception {
        logger.info("访问了controller");

        throw new ErrorPageException("666", "异常");
  }
}
