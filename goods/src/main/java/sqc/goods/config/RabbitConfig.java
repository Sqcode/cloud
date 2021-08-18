package sqc.goods.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 *
 *  publisher-confirm-type: correlated
 *  #确认消息已发送到队列(Queue)
 *  publisher-returns: true
 * ①消息推送到server，但是在server里找不到交换机 ==== ConfirmCallback 回调函数。
 * ②消息推送到server，找到交换机了，但是没找到队列 === ConfirmCallback和RetrunCallback两个回调函数。
 * ③消息推送到sever，交换机和队列啥都没找到 ===  ConfirmCallback 回调函数。
 * ④消息推送成功 ===  ConfirmCallback 回调函数。
 *
 * @author: Sqcode
 * @since: 2021/4/28 10:16
 */
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            System.out.println("ConfirmCallback:     "+"相关数据："+ correlationData);
//            System.out.println("ConfirmCallback:     "+"确认情况："+ ack);
//            System.out.println("ConfirmCallback:     "+"原因："+ cause);
        });

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("ReturnCallback:     "+"消息："+ message);
            System.out.println("ReturnCallback:     "+"回应码："+ replyCode);
            System.out.println("ReturnCallback:     "+"回应信息："+ replyText);
            System.out.println("ReturnCallback:     "+"交换机："+ exchange);
            System.out.println("ReturnCallback:     "+"路由键："+ routingKey);
        });

        return rabbitTemplate;
    }

}
