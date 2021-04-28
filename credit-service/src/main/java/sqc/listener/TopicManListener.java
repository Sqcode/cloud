package sqc.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

/**
 * @description：
 * @Author: Sqcode
 * @Date: 2021/4/28 9:19
 */
//@Component
//@RabbitListener(queues = "topic.man")
public class TopicManListener {

    @RabbitHandler
    public void process(Map msg) {
        System.out.println("TopicManReceiver消费者收到消息  : " + msg.toString());
    }
}
