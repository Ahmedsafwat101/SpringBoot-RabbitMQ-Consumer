package com.example.rabbitmq.rabbitmqconsumer.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String ROUTING_A = "routing.A";
    public static final String ROUTING_B = "routing.B";
    public static final String QUEUE_A = "queue.A";
    public static final String QUEUE_B = "queue.B";
    public static final String EXCHANGE_DIRECT = "exchange.direct";

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(EXCHANGE_DIRECT);
    }

    @Bean
    Queue queueA(){
         return new Queue(QUEUE_A,false);
    }

    @Bean
    Binding bindingForA(@Qualifier("queueA") Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_A);

    }

    @Bean
    Queue queueB(){
        return new Queue(QUEUE_B,false);
    }

    @Bean
    Binding bindingForB(@Qualifier("queueB") Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_B);

    }

    @Bean
    MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
       RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
       rabbitTemplate.setMessageConverter(converter());
       return rabbitTemplate;
    }

}
