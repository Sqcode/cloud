package sqc.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

/**
 * @description：
 * @Author: Sqcode
 * @Date: 2021/4/28 9:19
 */
//@Component
//@RabbitListener(queues = "topic.woman")
public class TopicAllListener {

    @RabbitHandler
    public void process(Map msg) {
        System.out.println("TopicAllListener消费者收到消息  : " + msg.toString());
    }
}
