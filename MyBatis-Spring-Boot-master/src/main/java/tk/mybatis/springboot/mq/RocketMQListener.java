package tk.mybatis.springboot.mq;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


public class RocketMQListener   implements MessageListenerConcurrently {

    protected  static  final Logger logger = LoggerFactory.getLogger(RocketMQListener.class);


    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt message : list) {
            logger.info("%s Receive New Messages: %s %n", Thread.currentThread().getName(), message);
            String msg = new String(message.getBody()); //从mq中取得消息
            logger.info("msg data from rocketMQ:" + msg);
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;  //消费成功，返回消息  失败：RECONSUME_LATER

    }
}
