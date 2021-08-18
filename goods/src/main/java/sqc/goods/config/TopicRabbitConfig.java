package sqc.goods.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description：主题交换机
 * @author: Sqcode
 * @since: 2021/4/28 9:08
 */
@Configuration
public class TopicRabbitConfig {
    // 绑定键
    public final static String MAN = "topic.man";
    public final static String WOMAN = "topic.woman";

    @Bean
    public Queue manQueue() {
        return new Queue(TopicRabbitConfig.MAN);
    }

    @Bean
    public Queue womanQueue() {
        return new Queue(TopicRabbitConfig.WOMAN);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 将manQueue和topicExchange绑定,而且绑定的键值为topic.man
     * 这样只要是消息携带的路由键是topic.man,才会分发到该队列
     * @return
     */
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(manQueue()).to(exchange()).with(MAN);
    }

    /**
     * 将womanQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
     * 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
     * @return
     */
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(womanQueue()).to(exchange()).with("topic.#");
    }

    /**
     * *  (星号) 用来表示一个单词 (必须出现的)
     * #  (井号) 用来表示任意数量（零个或多个）单词
     */
}
