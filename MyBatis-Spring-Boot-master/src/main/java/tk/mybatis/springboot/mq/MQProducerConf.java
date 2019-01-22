package tk.mybatis.springboot.mq;


import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MQProducerConf {


    protected  static  final Logger logger = LoggerFactory.getLogger(MQConsumer.class);

    /**
     * 生产者的组名
     */
    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;

    /**
     * 消费者的组名
     */
    @Value("${apache.rocketmq.consumer.consumerGroup}")
    private String consumerGroup;
//
    /**
     * NameServer 地址
     */
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;




    public void loanMsgProducer(String mqSendBody){
        logger.info("**********MQ消息处理开始发送处理***************");

        /**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setInstanceName(producerGroup);

        logger.info("对应的群组为： "+producerGroup);
        logger.info("MQ发送地址为： "+namesrvAddr);
        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        try {
            producer.start();
        } catch (MQClientException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        /**
         * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
         * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，<br>
         * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，<br>
         * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
         *
         */
        try {
            Message msg = new Message("UserInfo",// topic
                    "test01",// tag
                    "iv01",// key
                    (mqSendBody).getBytes());// body
            SendResult sendResult = producer.send(msg);
            logger.info("**MQ发送结果** "+ sendResult);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MQ消息处理发送处理异常!",e);
        }
//      try {
//		TimeUnit.MILLISECONDS.sleep(1000);
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
        /**
         * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
         * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
         */
        producer.shutdown();

        logger.info("**********MQ消息处理发送处理结束***************");

    }


}
