package tk.mybatis.springboot.mq.mqimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.springboot.mq.RocketMQConsumer;
import tk.mybatis.springboot.mq.RocketMQListener;

@Component
public class RocketMQConsumerCost {

  protected static final Logger logger = LoggerFactory.getLogger(RocketMQConsumerCost.class);
  /** NameServer 生产者注册地址 */
  @Value("${apache.rocketmq.namesrvAddr}")
  private String mqNameServer;

  /** 生产者的组名 */
  @Value("${apache.rocketmq.consumer.consumerGroup}")
  private String consumerGroup;

  /** 主题 */
  @Value("${apache.rocketmq.topic}")
  private String topic;

  // @Scheduled(cron = "0 0/1 * * * ?")
  @Scheduled(initialDelay = 1000, fixedDelay = Long.MAX_VALUE)
  public void consumerCost() {

    logger.info("开始拉取MQ消息");
    RocketMQListener mqListener = new RocketMQListener();

    RocketMQConsumer mqConsumer =
        new RocketMQConsumer(mqListener, mqNameServer, consumerGroup, topic);
    mqConsumer.init(); // 调用消费者

    try {
      Thread.sleep(1000 * 60L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
