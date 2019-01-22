package tk.mybatis.springboot.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.springboot.exception.ErrorPageException;
import tk.mybatis.springboot.exception.MyException ;
import tk.mybatis.springboot.mapper.RoleMapper;
import tk.mybatis.springboot.model.Role;
import tk.mybatis.springboot.mq.MQConsumer;
import tk.mybatis.springboot.mq.MQProducerConf;
import tk.mybatis.springboot.mq.mqimpl.RocketMQConsumerCost;
import tk.mybatis.springboot.mq.mqimpl.RocketMQProducerSend;

import java.util.HashMap;

@RestController
@RequestMapping("/role")
public class RoleController {

    protected  static  final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private  RoleMapper roleMapper;


    @Autowired
    private RocketMQProducerSend rocketMQProducerSend;

    @Autowired
    private RocketMQConsumerCost mqConsumer;

    @RequestMapping(value = "/find",produces = "application/json;charset=utf-8")
    public  String find(@RequestBody String reqData){

        logger.info(reqData);
        rocketMQProducerSend.sendMsgToMQServer(reqData);
      //  mqConsumer.consumerCost();

        Role one = roleMapper.findOne();
        System.out.println(one.getFunc());
        return  "222";


    }



    @RequestMapping("/log")
    public String Test(@RequestParam(value = "role") Integer role) throws Exception {
        logger.info("访问了controller");

             throw new ErrorPageException("666", "异常");

    }


}
