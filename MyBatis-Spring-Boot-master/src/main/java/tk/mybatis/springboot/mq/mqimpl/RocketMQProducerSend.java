package tk.mybatis.springboot.mq.mqimpl;

import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tk.mybatis.springboot.mq.RocketMQProducer;
@Component
public class RocketMQProducerSend {

    protected  static  final Logger logger = LoggerFactory.getLogger(RocketMQProducerSend.class);
    /**
     * NameServer  生产者注册地址
     */
    @Value("${apache.rocketmq.namesrvAddr}")
    private String mqNameServer;

    /**
     * 生产者的组名
     */
    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerMqGroupName;

    /**
     * 主题
     */
    @Value("${apache.rocketmq.topic}")
    private String topic;

    /**
     * 标签tag
     */
    @Value("${apache.rocketmq.tag}")
    private String tag;


    /**
     * 主键key
     */
    @Value("${apache.rocketmq.key}")
    private String key;





    public String sendMsgToMQServer(String msg) {

        RocketMQProducer mqProducer = new RocketMQProducer(mqNameServer, producerMqGroupName, topic);


        mqProducer.init();

        Message message = new Message(topic,tag,key,(msg).getBytes());// body

        message.setBody((msg).getBytes());

        mqProducer.send(message);  //发送消息

        return msg;
    }

}








