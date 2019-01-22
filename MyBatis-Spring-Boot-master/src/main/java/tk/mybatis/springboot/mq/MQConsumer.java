package tk.mybatis.springboot.mq;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.springboot.controller.RoleController;

import java.util.List;
@Component
public class MQConsumer {

    protected  static  final Logger logger = LoggerFactory.getLogger(MQConsumer.class);

    /**
     * NameServer 地址
     */
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;


    /**
     * 消费者的组名
     */
    @Value("${apache.rocketmq.consumer.consumerGroup}")
    private String consumerGroup;


    /*
     * 费用收取,MQ消息接受及处理
     */

    @Scheduled(initialDelay = 1000, fixedDelay = Long.MAX_VALUE)
    public void ConsumMq() throws Exception{
        logger.info("开始MQ消息拉取处理");
        //消费者的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();

        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setInstanceName(consumerGroup);


        logger.info("消费者GROUP"+ consumerGroup);
        logger.info("拉取的地址为"+ namesrvAddr);
        try {
            //订阅对应Topic下Tag的消息
            consumer.subscribe("UserInfo","test01");

            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
            //如果非第一次启动，那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(new MessageListenerConcurrently() {


                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                    try {
                        for (MessageExt messageExt : list) {

                            logger.info("输出消息内容为messageExt： "+ messageExt);

                            String messageBody = new String(messageExt.getBody(), "utf-8");

                            logger.info("消费响应：Msg: " + messageExt.getMsgId() + ",报文消息体msgBody: " + messageBody);
                            if(messageBody != null){
                                try {

                                } catch (Exception e) {
                                    logger.error("处理异常！", e);
                                }
                            }else{
                                logger.error("WARNING:消息体为空请及时检查！！！");
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
                }
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("**********MQ消息拉取结束***************");
    }
}
