package tk.mybatis.springboot.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.UUID;


public class RocketMQConsumer {

    protected  static  final Logger logger = LoggerFactory.getLogger(RocketMQConsumer.class);

    private DefaultMQPushConsumer defaultMQPushConsumer;

    private MessageListener messageListener;

    private String nameServer;

    private String groupName;

    private String topics;


    public RocketMQConsumer(MessageListener listener, String nameServer, String groupName, String topics) {
        this.messageListener = listener;
        this.nameServer = nameServer;
        this.groupName = groupName;
        this.topics = topics;
    }



    public  void init(){

        defaultMQPushConsumer = new DefaultMQPushConsumer(groupName);
        defaultMQPushConsumer.setNamesrvAddr(nameServer);

        try {
            defaultMQPushConsumer.subscribe(topics,"*");
        } catch (MQClientException e) {
            logger.error("初始化消费者异常:"+e);
        }

        defaultMQPushConsumer.setInstanceName(UUID.randomUUID().toString());
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) this.messageListener);


        try {
            defaultMQPushConsumer.start();  //启动消费者
        } catch (MQClientException e) {
            logger.error("启动消费者异常:"+e);
        }

        logger.info("RocketMQConsumer Started! group=" + defaultMQPushConsumer.getConsumerGroup() + " instance=" + defaultMQPushConsumer.getInstanceName()
        );


    }
}
