package sqc.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

/**
 * @Description:
 * @author: Sqcode
 * @since: 2021/4/28 9:56
 */
//@Component
//@RabbitListener(queues = "fanout.B")
public class FanoutBListener {

    @RabbitHandler
    public void process(Map msg) {
        System.out.println("FanoutListener - B消费者收到消息  : " + msg.toString());
    }
}
