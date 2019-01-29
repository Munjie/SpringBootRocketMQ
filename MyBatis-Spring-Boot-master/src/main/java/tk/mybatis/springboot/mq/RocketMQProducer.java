package tk.mybatis.springboot.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class RocketMQProducer {

    protected static final Logger logger = LoggerFactory.getLogger(RocketMQProducer.class);

    private DefaultMQProducer sender;

    protected String nameServer;

    protected String groupName;

    protected String topics;

    // 初始化生产者
    public void init() {
        sender = new DefaultMQProducer(groupName);
        sender.setNamesrvAddr(nameServer);
        sender.setInstanceName(UUID.randomUUID().toString());
        try {
            sender.start(); // 启动生产者
        } catch (MQClientException e) {
            logger.error("initialization producer exception:" + e);
        }
    }

    public RocketMQProducer(String nameServer, String groupName, String topics) {
        this.nameServer = nameServer;
        this.groupName = groupName;
        this.topics = topics;
    }

    // 发送消息
    public void send(Message message) {

        message.setTopic(topics);

        try {
            SendResult result = sender.send(message);
            SendStatus status = result.getSendStatus();
            logger.info("messageId=" + result.getMsgId() + ", status=" + status);
        } catch (Exception e) {
            logger.error("producer send message exception:" + e);
        }
    }
}
