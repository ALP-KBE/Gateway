package ALP.KBEGateway.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    static final String TOPIC_EXCHANGE_NAME = "component-exchange";

    @Bean
    public Queue warehouseQueue() {
        return new Queue("warehouse-queue");
    }
    
    @Bean
    public Queue priceQueue() {
        return new Queue("price-queue");
    }

    @Bean
    public Queue productQueue() {
        return new Queue("product-queue");
    }

    @Bean
    public Queue mainQueue() {
        return new Queue("main-queue");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Binding warehouseBinding(Queue warehouseQueue, TopicExchange exchange) {
        return BindingBuilder.bind(warehouseQueue).to(exchange).with("warehouse-key");
    }
    
    @Bean
    public Binding priceBinding(Queue priceQueue, TopicExchange exchange) {
        return BindingBuilder.bind(priceQueue).to(exchange).with("price-key");
    }

    @Bean
    public Binding productBinding(Queue productQueue, TopicExchange exchange) {
        return BindingBuilder.bind(productQueue).to(exchange).with("product-key");
    }

    @Bean
    public Binding mainBinding(Queue mainQueue, TopicExchange exchange) {
        return BindingBuilder.bind(mainQueue).to(exchange).with("main-key");
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
