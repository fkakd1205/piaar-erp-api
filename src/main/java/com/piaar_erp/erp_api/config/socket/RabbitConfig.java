package com.piaar_erp.erp_api.config.socket;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
//    private static final String EXCHANGE_ORDER_ITEM = "amq.topic";
//    private static final String QUEUE_ORDER_ITEM = "erp.order-item.queue";
//    private static final String ROUTING_KEY_ORDER_ITEM = "erp.order-item";
//
//    @Bean
//    Queue queue() {
//        return new Queue(QUEUE_ORDER_ITEM, false);
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(EXCHANGE_ORDER_ITEM);
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_ORDER_ITEM);
//    }
//
//    @Bean
//    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(messageConverter());
//        return rabbitTemplate;
//    }
//
//    @Bean
//    MessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
}
