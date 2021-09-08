//package sqc.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 直连交换机 (此处作为消费者)
// * 其实可以不用添加这个配置，直接建后面的监听就好，使用注解来让监听器监听对应的队列即可。配置上了的话，其实消费者也是生成者的身份，也能推送该消息。
// * @RabbitListener(queues = "TestDirectQueue")
// */
//@Configuration
//public class DirectRabbitConfig {
//
//    /**
//     *
//     * @return 队列：TestDirectQueue
//     */
//    @Bean
//    public Queue TestDirectQueue() {
//        // durable:是否持久化，默认是false。持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
//        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
//        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
//        //   return new Queue("TestDirectQueue", true, false, false);
//
//        // 一般设置一下队列的持久化就好,其余两个就是默认false
//        return new Queue("TestDirectQueue", true);
//    }
//
//    /**
//     *
//     * @return Direct交换机：TestDirectExchange
//     */
//    @Bean
//    DirectExchange TestDirectExchange() {
//        //  return new DirectExchange("TestDirectExchange", true, true);
//        return new DirectExchange("TestDirectExchange", true, false);
//    }
//
//    /**
//     *
//     * @return 绑定-将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
//     */
//    @Bean
//    Binding bindingDirect() {
//        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
//    }
//
//}
