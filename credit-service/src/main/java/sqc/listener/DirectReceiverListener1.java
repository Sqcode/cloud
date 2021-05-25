package sqc.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

/**
 *
 * @description: 监听的队列名称 TestDirectQueue
 * @author: Sqcode
 * @since: 2021/4/28 9:00
 */
//@Component
//@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiverListener1 {

    @RabbitHandler
    public void process(Map msg) {
        System.out.println("DirectReceiver1消费者收到消息  : " + msg.toString());
    }

}
